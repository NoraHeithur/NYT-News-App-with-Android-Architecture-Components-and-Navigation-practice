package com.nora.nytnewsapps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.nora.nytnewsapps.R
import com.nora.nytnewsapps.databinding.ActivityMainBinding
import com.nora.nytnewsapps.presentation.utils.setupWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.appbarTopMain)
        if (savedInstanceState == null) {
            setupBottomNavigationBarWithNavController()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBarWithNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBarWithNavController() {
        val bottomNavigationView = binding.btNavViewMain
        val navGraphIds = listOf(
                R.navigation.nav_graph_today,
                R.navigation.nav_graph_saved,
                R.navigation.nav_graph_about_me
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.app_nav_host_fragment,
            intent = intent
        )

        // setup the action bar whenever the selected controller changes
//        controller.observe(this) { navController ->
//            setupActionBarWithNavController(navController)
//        }
        currentNavController = controller
    }
}