package com.nora.nytnewsapps.presentation.screens.today

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.nora.nytnewsapps.R
import com.nora.nytnewsapps.databinding.FragmentTodayBinding
import com.nora.nytnewsapps.data.network.NYTNewsApi

class TodayFragment : Fragment() {

    private lateinit var binding: FragmentTodayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.today_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today, container, false)
        return binding.root
    }
}