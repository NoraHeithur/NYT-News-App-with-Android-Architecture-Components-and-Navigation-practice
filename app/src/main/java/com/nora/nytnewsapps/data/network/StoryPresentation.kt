package com.nora.nytnewsapps.data.network

sealed class StoryPresentation {
    abstract val id: Long
}

data class StoryExpendedArticle(
        override val id: Long,
        val section: String,
        val title: String,
        val summary: String,
        val author: String,
        val published: String,
        val imageUrl: String,
        val caption: String,
        val url: String
) : StoryPresentation()

data class StorySmallArticle(
        override val id: Long,
        val section: String,
        val title: String,
        val summary: String,
        val author: String,
        val published: String,
        val imageUrl: String,
        val caption: String,
        val url: String
) : StoryPresentation()

fun NetworkStory.toItemViewExpanded(): StoryExpendedArticle {
    return StoryExpendedArticle(
            id = this.id,
            section = this.section,
            title = this.title,
            summary = this.summary,
            author = this.author,
            published = this.published,
            imageUrl = this.imageLarge,
            caption = this.caption,
            url = this.url
    )
}

fun NetworkStory.toItemViewSmall(): StorySmallArticle {
    return StorySmallArticle(
            id = this.id,
            section = this.section,
            title = this.title,
            summary = this.summary,
            author = this.author,
            published = this.published,
            imageUrl = this.imageStandard,
            caption = this.caption,
            url = this.url
    )
}