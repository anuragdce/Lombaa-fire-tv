package com.freemovies.firetv.data

import com.freemovies.firetv.base.data.local.model.RequestResult
import com.freemovies.firetv.data.local.model.Movie
import com.freemovies.firetv.data.local.model.PlayList

interface MoviesRepository {

    suspend fun load(): RequestResult<Unit>

    suspend fun getPlayList(): List<PlayList>

    suspend fun getMovie(id: String): Movie?

    suspend fun update(movie: Movie)
}