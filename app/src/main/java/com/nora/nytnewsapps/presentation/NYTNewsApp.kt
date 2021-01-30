package com.nora.nytnewsapps.presentation

import android.app.Application
import timber.log.Timber

class NYTNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}

// TODO create ViewModel, navigation safe arg, and ViewModel Factory
// TODO set up UI with data binding, recycler view adapter and view holder
// TODO create Repository for online data and caching data
// TODO add Coroutines to project