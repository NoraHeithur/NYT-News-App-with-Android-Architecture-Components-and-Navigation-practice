package com.nora.nytnewsapps.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nora.nytnewsapps.data.database.DatabaseStory
import com.nora.nytnewsapps.presentation.screens.saved.SavedAdapter
import com.nora.nytnewsapps.data.network.StoryPresentation
import com.nora.nytnewsapps.presentation.screens.today.TodayAdapter

@BindingAdapter(value = ["setArticleImage", "setPlaceHolder"], requireAll = false)
fun setArticleImage(imageView: ImageView, url: String?, placeholder: Drawable?) {
    when (url) {
        null -> imageView.visibility = View.GONE
        else -> setImageFromUrl(url, imageView, placeholder)
    }
}

//@BindingAdapter("setSavedArticlePublishedDate")
//fun setSavedArticlePublishedDate(item: StoryEntity?) {
//    item?.let {
//        TODO add time formatter
//    }
//}

@BindingAdapter("setSavedListData")
fun setSavedListData(recyclerView: RecyclerView, data: List<DatabaseStory>?) {
    val adapter = recyclerView.adapter as SavedAdapter
    adapter.submitList(data)
}

@BindingAdapter("setTodayNewsListData")
fun setTodayNewsListData(recyclerView: RecyclerView, data: List<StoryPresentation>) {
    val adapter = recyclerView.adapter as TodayAdapter
    adapter.submitList(data)
}