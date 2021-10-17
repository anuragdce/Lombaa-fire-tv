package com.lombaa.firetv.data.remote

import androidx.annotation.WorkerThread
import com.lombaa.firetv.base.data.local.model.RequestResult
import com.lombaa.firetv.data.local.model.Movie

interface RemoteDataSource {
    @WorkerThread
    suspend fun getMovies(): RequestResult<List<Movie>>

    @WorkerThread
    suspend fun getTags(): List<String>
}