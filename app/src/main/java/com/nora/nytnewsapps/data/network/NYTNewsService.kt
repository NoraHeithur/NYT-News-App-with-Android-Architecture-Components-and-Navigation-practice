package com.nora.nytnewsapps.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTNewsService {

    @GET("svc/topstories/v2/{section}.json")
    fun getNewsToday(@Path("section") section: String, @Query("api_key") api_key: String = ApiKey.apiKey()): Deferred<TopStories>
}