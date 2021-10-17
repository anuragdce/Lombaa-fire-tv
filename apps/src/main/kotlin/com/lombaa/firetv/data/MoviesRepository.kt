package com.lombaa.firetv.data

import com.lombaa.firetv.base.data.local.model.RequestResult
import com.lombaa.firetv.data.local.model.Movie
import com.lombaa.firetv.data.local.model.PlayList

interface MoviesRepository {

    suspend fun load(): RequestResult<Unit>

    suspend fun getPlayList(): List<PlayList>

    suspend fun getMovie(id: String): Movie?

    suspend fun update(movie: Movie)
}