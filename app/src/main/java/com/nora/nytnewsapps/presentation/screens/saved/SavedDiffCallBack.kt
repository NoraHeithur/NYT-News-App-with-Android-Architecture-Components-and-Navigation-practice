package com.nora.nytnewsapps.presentation.screens.saved

import androidx.recyclerview.widget.DiffUtil
import com.nora.nytnewsapps.data.database.DatabaseStory

class SavedDiffCallBack : DiffUtil.ItemCallback<DatabaseStory>() {
    override fun areItemsTheSame(oldItem: DatabaseStory, newItem: DatabaseStory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatabaseStory, newItem: DatabaseStory): Boolean {
        return oldItem == newItem
    }
}