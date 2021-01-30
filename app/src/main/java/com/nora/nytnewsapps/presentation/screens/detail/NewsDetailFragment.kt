package com.nora.nytnewsapps.presentation.screens.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.nora.nytnewsapps.R
import com.nora.nytnewsapps.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_stories_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNewsDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false)
        return binding.root
    }
}