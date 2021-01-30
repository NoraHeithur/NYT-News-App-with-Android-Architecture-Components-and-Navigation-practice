package com.nora.nytnewsapps.presentation.screens.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nora.nytnewsapps.data.database.DatabaseStory
import com.nora.nytnewsapps.databinding.ItemSaveListBinding
import com.nora.nytnewsapps.presentation.screens.saved.holder.SavedViewHolder

class SavedAdapter(private val clickListener: SavedStoryOnClickListener) : ListAdapter<DatabaseStory, SavedViewHolder>(SavedDiffCallBack()) {

    private fun createSavedViewHolder(parent: ViewGroup): SavedViewHolder {
        return SavedViewHolder(ItemSaveListBinding
                .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return createSavedViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}

class SavedStoryOnClickListener(val clickListener: (storyId: Long) -> Unit) {
    fun onClick(itemClicked: DatabaseStory) = clickListener(itemClicked.id)
}