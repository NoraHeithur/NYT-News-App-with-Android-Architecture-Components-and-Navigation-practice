package com.nora.nytnewsapps.data.network

import com.nora.nytnewsapps.data.database.DatabaseStory
import com.nora.nytnewsapps.presentation.utils.isBlankOrNotBlank

fun List<DatabaseStory>.asDomain(): List<NetworkStory> {
    return map {
        NetworkStory(
                id = it.url.hashCode().toLong(),
                section = it.section,
                title = it.title,
                summary = it.summary,
                author = it.author,
                published = it.published,
                imageStandard = it.imageStandard,
                imageLarge = it.imageLarge,
                imageJumbo = it.imageJumbo,
                caption = it.caption,
                url = it.url
        )
    }
}

fun TopStories.asDomain(): List<NetworkStory> {
    return results.map {
        NetworkStory(
                id = it.url.hashCode().toLong(),
                section = it.section,
                title = it.title,
                summary = it.summary,
                author = it.author,
                published = it.publishedDate,
                imageStandard = it.multimedia.find { it.format == "Standard Thumbnail" }?.url ?: "",
                imageLarge = it.multimedia.find { it.format == "thumbLarge" }?.url ?: "",
                imageJumbo = it.multimedia.find { it.format == "superJumbo" }?.url ?: "",
                caption = it.multimedia.find { it.caption.isBlankOrNotBlank() }!!.caption,
                url = it.url
        )
    }
}

fun TopStories.asDatabase(): Array<DatabaseStory> {
    return results.map {
        DatabaseStory(
                id = it.url.hashCode().toLong(),
                section = it.section,
                title = it.title,
                summary = it.summary,
                author = it.author,
                published = it.publishedDate,
                imageStandard = it.multimedia.find { it.format == "Standard Thumbnail" }?.url
                        ?: "",
                imageLarge = it.multimedia.find { it.format == "thumbLarge" }?.url ?: "",
                imageJumbo = it.multimedia.find { it.format == "superJumbo" }?.url ?: "",
                caption = it.multimedia.find { it.caption.isBlankOrNotBlank() }!!.caption,
                url = it.url
        )
    }.toTypedArray()
}