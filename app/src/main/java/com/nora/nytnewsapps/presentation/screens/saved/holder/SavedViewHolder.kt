package com.nora.nytnewsapps.presentation.screens.saved.holder

import androidx.recyclerview.widget.RecyclerView
import com.nora.nytnewsapps.data.database.DatabaseStory
import com.nora.nytnewsapps.databinding.ItemSaveListBinding
import com.nora.nytnewsapps.presentation.screens.saved.SavedStoryOnClickListener

class SavedViewHolder (private val binding: ItemSaveListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DatabaseStory, clickListener: SavedStoryOnClickListener) {
        binding.savedStory = item
        binding.onItemClickListener = clickListener
        binding.executePendingBindings()
    }
}