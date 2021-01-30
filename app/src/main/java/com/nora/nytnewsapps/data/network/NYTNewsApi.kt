package com.nora.nytnewsapps.data.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.nytimes.com/"

private val moshi = Moshi.Builder().build()

object NYTNewsApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val newsService: NYTNewsService = retrofit.create(NYTNewsService::class.java)
}