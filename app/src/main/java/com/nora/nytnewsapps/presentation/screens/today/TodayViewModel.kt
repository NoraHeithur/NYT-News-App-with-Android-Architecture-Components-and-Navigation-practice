package com.nora.nytnewsapps.presentation.screens.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nora.nytnewsapps.data.database.StoryDatabase
import com.nora.nytnewsapps.data.network.StoryPresentation
import com.nora.nytnewsapps.data.network.TopStories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodayViewModel(val database: StoryDatabase) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _topStoriesData = MutableLiveData<List<StoryPresentation>>()
    val topStoriesData: LiveData<List<StoryPresentation>>
        get() = _topStoriesData

}