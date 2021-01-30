package com.nora.nytnewsapps.presentation.screens.today

import androidx.recyclerview.widget.DiffUtil
import com.nora.nytnewsapps.data.network.StoryPresentation

class TodayDiffCallBack : DiffUtil.ItemCallback<StoryPresentation>() {

    override fun areItemsTheSame(oldItem: StoryPresentation, newItem: StoryPresentation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoryPresentation, newItem: StoryPresentation): Boolean {
        return oldItem == newItem
    }
}