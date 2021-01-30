package com.nora.nytnewsapps.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class DatabaseStory(
        @PrimaryKey
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