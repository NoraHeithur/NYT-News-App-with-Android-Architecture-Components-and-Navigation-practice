package com.nora.nytnewsapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
        val id: Long,
        val section: String,
        val title: String,
        val summary: String,
        val author: String,
        val published: String,
        val imageStandard: String,
        val imageLarge: String,
        val imageJumbo: String,
        val caption: String,
        val url: String
) : Parcelable