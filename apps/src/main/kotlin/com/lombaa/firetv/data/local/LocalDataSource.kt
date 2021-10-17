package com.lombaa.firetv.data.local

import androidx.annotation.WorkerThread
import com.lombaa.firetv.data.local.model.Movie

interface LocalDataSource {
    @WorkerThread
    suspend fun save(movie: Movie)

    @WorkerThread
    suspend fun save(movies: List<Movie>)

    @WorkerThread
    suspend fun getMovies(tag: String): List<Movie>

    @WorkerThread
    suspend fun getMovies(): List<Movie>

    @WorkerThread
    suspend fun getMovie(id: String): Movie?
}