package com.nora.nytnewsapps.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun setImageFromUrl(url: String,imageView: ImageView, placeholder: Drawable?) {
    Glide.with(imageView)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder))
            .into(imageView)
}