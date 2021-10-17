package com.freemovies.firetv.data.remote

import androidx.annotation.WorkerThread
import com.freemovies.firetv.base.data.local.model.RequestResult
import com.freemovies.firetv.data.local.model.Movie

interface RemoteDataSource {
    @WorkerThread
    suspend fun getMovies(): RequestResult<List<Movie>>

    @WorkerThread
    suspend fun getTags(): List<String>
}