package com.nora.nytnewsapps.data.network

object ApiKey {
    init {
        System.loadLibrary("native-lib")
    }
    external fun apiKey(): String
}