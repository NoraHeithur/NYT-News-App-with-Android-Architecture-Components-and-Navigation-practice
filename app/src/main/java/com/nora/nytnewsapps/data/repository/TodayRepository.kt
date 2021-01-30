package com.nora.nytnewsapps.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nora.nytnewsapps.data.database.StoryDatabase
import com.nora.nytnewsapps.data.network.NYTNewsApi
import com.nora.nytnewsapps.data.network.NetworkStory
import com.nora.nytnewsapps.data.network.asDatabase
import com.nora.nytnewsapps.data.network.asDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodayRepository(private val database: StoryDatabase) {

    val stories: LiveData<List<NetworkStory>> = Transformations.map(database.storyDao.getSavedStories()) {
        it.asDomain()
    }

    suspend fun getTopStories(section: String = "home") {
        withContext(Dispatchers.IO) {
            val topStories = NYTNewsApi.newsService.getNewsToday(section).await()
            database.storyDao.insert(*topStories.asDatabase())
        }
    }
}