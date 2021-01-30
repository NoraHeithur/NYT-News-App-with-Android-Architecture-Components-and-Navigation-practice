package com.nora.nytnewsapps.data.network

import android.os.Parcelable
import com.nora.nytnewsapps.presentation.utils.isBlankOrNotBlank
import kotlinx.parcelize.Parcelize

data class NetworkStory(
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
)