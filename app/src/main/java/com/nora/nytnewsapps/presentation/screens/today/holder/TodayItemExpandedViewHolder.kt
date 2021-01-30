package com.nora.nytnewsapps.presentation.screens.today.holder

import androidx.recyclerview.widget.RecyclerView
import com.nora.nytnewsapps.databinding.ItemTodayListExpandBinding
import com.nora.nytnewsapps.data.network.StoryExpendedArticle
import com.nora.nytnewsapps.presentation.screens.today.TodayItemOnClickListener

class TodayItemExpandedViewHolder (private val binding: ItemTodayListExpandBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: StoryExpendedArticle, clickListener: TodayItemOnClickListener) {
        binding.articleExpanded = item
        binding.onItemClickListener = clickListener
        binding.executePendingBindings()
    }
}