package com.lombaa.firetv.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

internal interface MoviesApi {
    @GET("/roku-rss.json?__site=freemoviesplus&limit=2046&format=hls")
    suspend fun download(): Response<ResponseBody>
}