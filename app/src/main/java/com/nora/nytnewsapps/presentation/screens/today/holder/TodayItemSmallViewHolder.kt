package com.nora.nytnewsapps.presentation.screens.today.holder

import androidx.recyclerview.widget.RecyclerView
import com.nora.nytnewsapps.databinding.ItemTodayListSmallBinding
import com.nora.nytnewsapps.data.network.StorySmallArticle
import com.nora.nytnewsapps.presentation.screens.today.TodayItemOnClickListener

class TodayItemSmallViewHolder (private val binding: ItemTodayListSmallBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: StorySmallArticle, clickListener: TodayItemOnClickListener) {
        binding.articleSmall = item
        binding.onItemClickListener = clickListener
        binding.executePendingBindings()
    }
}