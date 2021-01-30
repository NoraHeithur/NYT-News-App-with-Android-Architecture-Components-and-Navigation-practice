package com.nora.nytnewsapps.presentation.utils

import android.content.Intent
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nora.nytnewsapps.R
import timber.log.Timber

/**
 * Manage the various graph needed for a [BottomNavigationView]
 * Support multiple back stacks
 * Ref. from
 * https://github.com/android/architecture-components-samples/blob/master/NavigationAdvancedSample
 * /app/src/main/java/com/example/android/navigationadvancedsample/NavigationExtensions.kt
 * Thank you.
 */

fun BottomNavigationView.setupWithNavController(
        navGraphIds: List<Int>,
        fragmentManager: FragmentManager,
        containerId: Int,
        intent: Intent
): LiveData<NavController> {

    // Map of tags
    val graphIdToTagMap = SparseArray<String>()
    // Mutable live data with the selected controlled
    val selectNavController = MutableLiveData<NavController>()

    var firstFragmentGraphId = 0

    // First create a NavHostFragment for each NavGraph ID
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)
        Timber.i("Now this fragment: ${getFragmentTag(index)}")

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
                fragmentManager,
                fragmentTag,
                navGraphId,
                containerId
        )

        // Obtain its id
        val graphId = navHostFragment.navController.graph.id

        if (index == 0) {
            firstFragmentGraphId = graphId
        }

        // Save to the map
        graphIdToTagMap[graphId] = fragmentTag

        // Attach or Detach nav host fragment depending on whether it's the selected item
        if (this.selectedItemId == graphId) {
            // Update live data with the selected graph
            selectNavController.value = navHostFragment.navController
            attachNavHostFragment(fragmentManager, navHostFragment, index == 0)
        } else {
            detachNavHostFragment(fragmentManager, navHostFragment)
        }
    }

    // Connect selecting an item with swapping Fragments
    var selectedItemTag = graphIdToTagMap[this.selectedItemId]
    val firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
    var isOnFirstFragment = selectedItemTag == firstFragmentTag

    // When a navigation item is selected
    setOnNavigationItemSelectedListener { item ->
        if (fragmentManager.isStateSaved) {
            false
        } else {
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
            if (selectedItemTag != newlySelectedItemTag) {
                // Pop everything above the first fragment (the fixed first start destination)
                fragmentManager.popBackStack(
                        firstFragmentTag,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                val selectedFragment =
                        fragmentManager.findFragmentByTag(newlySelectedItemTag) as NavHostFragment

                // Exclude the first fragment tag because it's always in the back stack
                if (firstFragmentTag != newlySelectedItemTag) {
                    // Commit a transaction that cleans the stack and adds the first fragment
                    // to it, creating the fixed started destination
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(
//                            R.anim.nav_default_enter_anim,
//                            R.anim.nav_default_exit_anim,
//                            R.anim.nav_default_pop_enter_anim,
//                            R.anim.nav_default_pop_exit_anim
                                    R.anim.fragment_fade_enter,
                                    R.anim.fragment_fade_exit,
                                    R.anim.fragment_fade_enter,
                                    R.anim.fragment_fade_exit
                            )
                            .attach(selectedFragment)
                            .setPrimaryNavigationFragment(selectedFragment)
                            .apply {
                                // Detach all other Fragment
                                graphIdToTagMap.forEach { _, fragmentTagIter ->
                                    if (fragmentTagIter != newlySelectedItemTag) {
                                        detach(fragmentManager.findFragmentByTag(firstFragmentTag)!!)
                                    }
                                }
                            }
                            .addToBackStack(firstFragmentTag)
                            .setReorderingAllowed(true)
                            .commit()
                }
                selectedItemTag = newlySelectedItemTag
                isOnFirstFragment = selectedItemTag == firstFragmentTag
                selectNavController.value = selectedFragment.navController
                true
            } else {
                false
            }
        }
    }

    // Option: on item reselected, pop back stack to the destination of the graph
    setupItemReselected(graphIdToTagMap, fragmentManager)

    // Handle deep link
    setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)

    // Finally, ensure that update BottomNavigationView when the back stack changes
    fragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !fragmentManager.isOnBackStack(firstFragmentTag)) {
            this.selectedItemId = firstFragmentGraphId
        }

        // Reset the graph if the currentDestination is not valid (happens when the back stack is popped
        // after using the back button)
        selectNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }
    return selectNavController
}

private fun BottomNavigationView.setupItemReselected(
        graphIdToTagMap: SparseArray<String>,
        fragmentManager: FragmentManager
) {
    setOnNavigationItemReselectedListener { item ->
        val newlySelectedItemTag = graphIdToTagMap[item.itemId]
        val selectedFragment =
                fragmentManager.findFragmentByTag(newlySelectedItemTag) as NavHostFragment
        val navController = selectedFragment.navController
        // Pop the back stack to the start destination of the current navController graph
        navController.popBackStack(
                navController.graph.startDestination, false
        )
    }
}

private fun BottomNavigationView.setupDeepLinks(
        navGraphIds: List<Int>,
        fragmentManager: FragmentManager,
        containerId: Int,
        intent: Intent
) {
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the navigation host fragment
        val navHostFragment = obtainNavHostFragment(
                fragmentManager,
                fragmentTag,
                navGraphId,
                containerId
        )
        // Handle Intent
        if (navHostFragment.navController.handleDeepLink(intent)
                && selectedItemId != navHostFragment.navController.graph.id) {
            this.selectedItemId = navHostFragment.navController.graph.id
        }
    }
}

private fun obtainNavHostFragment(
        fragmentManager: FragmentManager,
        fragmentTag: String,
        navGraphId: Int,
        containerId: Int
): NavHostFragment {
    //If the Nav Host Fragment exists, return it
    val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
    existingFragment?.let { return it }

    //Otherwise, create it and return it
    val navHostFragment = NavHostFragment.create(navGraphId)
    fragmentManager.beginTransaction()
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
    return navHostFragment
}

private fun attachNavHostFragment(
        fragmentManager: FragmentManager,
        navHostFragment: NavHostFragment,
        isPrimaryNavFragment: Boolean
) {
    fragmentManager.beginTransaction()
            .attach(navHostFragment)
            .apply {
                if (isPrimaryNavFragment) {
                    setPrimaryNavigationFragment(navHostFragment)
                }
            }.commitNow()
}

private fun detachNavHostFragment(
        fragmentManager: FragmentManager,
        navHostFragment: NavHostFragment
) {
    fragmentManager.beginTransaction()
            .detach(navHostFragment)
            .commitNow()
}

private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    val backStackCount = backStackEntryCount
    for (index in 0 until backStackCount) {
        if (getBackStackEntryAt(index).name == backStackName) {
            return true
        }
    }
    return false
}

private fun getFragmentTag(index: Int) = "bottomNavigation#$index"