package com.nora.nytnewsapps.presentation.screens.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nora.nytnewsapps.R
import com.nora.nytnewsapps.data.network.StoryExpendedArticle
import com.nora.nytnewsapps.data.network.StoryPresentation
import com.nora.nytnewsapps.data.network.StorySmallArticle
import com.nora.nytnewsapps.databinding.ItemTodayListExpandBinding
import com.nora.nytnewsapps.databinding.ItemTodayListSmallBinding
import com.nora.nytnewsapps.presentation.screens.today.holder.TodayItemExpandedViewHolder
import com.nora.nytnewsapps.presentation.screens.today.holder.TodayItemSmallViewHolder
import java.lang.IllegalArgumentException

class TodayAdapter(private val clickListener: TodayItemOnClickListener) : ListAdapter<StoryPresentation, RecyclerView.ViewHolder>(TodayDiffCallBack()) {

    private fun createExpandViewHolder(parent: ViewGroup): TodayItemExpandedViewHolder {
        return TodayItemExpandedViewHolder(ItemTodayListExpandBinding
                .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ))

    }

    private fun createSmallViewHolder(parent: ViewGroup): TodayItemSmallViewHolder {
        return TodayItemSmallViewHolder(ItemTodayListSmallBinding
                .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_EXPANDED -> createExpandViewHolder(parent)
            ITEM_VIEW_SMALL -> createSmallViewHolder(parent)
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodayItemExpandedViewHolder -> {
                val expandedItem = getItem(position) as StoryExpendedArticle
                holder.bind(expandedItem, clickListener)
            }

            is TodayItemSmallViewHolder -> {
                val smallItem = getItem(position) as StorySmallArticle
                holder.bind(smallItem, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StoryExpendedArticle -> ITEM_VIEW_EXPANDED
            is StorySmallArticle -> ITEM_VIEW_SMALL
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    companion object {
        const val ITEM_VIEW_EXPANDED = R.layout.item_today_list_expand
        const val ITEM_VIEW_SMALL = R.layout.item_today_list_small
    }
}

class TodayItemOnClickListener(val clickListener: (itemId: Long) -> Unit) {
    fun onItemExpandedClick(itemClicked: StoryExpendedArticle) = clickListener(itemClicked.id)
    fun onItemSmallClick(itemClicked: StoryExpendedArticle) = clickListener(itemClicked.id)
}