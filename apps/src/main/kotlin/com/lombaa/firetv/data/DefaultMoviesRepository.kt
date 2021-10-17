package com.lombaa.firetv.data

import android.content.Context
import com.lombaa.firetv.R
import com.lombaa.firetv.base.data.local.model.RequestResult
import com.lombaa.firetv.base.data.local.model.data
import com.lombaa.firetv.base.data.local.model.succeeded
import com.lombaa.firetv.data.local.LocalDataSource
import com.lombaa.firetv.data.local.model.Movie
import com.lombaa.firetv.data.local.model.PlayList
import com.lombaa.firetv.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultMoviesRepository(
    private val context: Context,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MoviesRepository {

    override suspend fun load(): RequestResult<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = remoteDataSource.getMovies()
                if (response.succeeded) {
                    localDataSource.save(response.data!!)
                    RequestResult.Success(Unit)
                } else {
                    RequestResult.Error.defaultRequestError()
                }
            } catch (exception: Exception) {
                RequestResult.Error(exception)
            }
        }
    }

    override suspend fun getPlayList(): List<PlayList> {
        return withContext(Dispatchers.IO) {
            val playList = mutableListOf<PlayList>()
            val movies = localDataSource.getMovies()
            playList.add(PlayList(context.getString(R.string.recent_movies), movies))
            val tags = remoteDataSource.getTags()
            tags.forEach {
                val movies = localDataSource.getMovies(it)
                if (movies.isNotEmpty()) {
                    playList.add(PlayList(it, movies))
                }
            }
            playList
        }
    }

    override suspend fun getMovie(id: String): Movie? {
        return withContext(Dispatchers.IO) {
            localDataSource.getMovie(id)
        }
    }

    override suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) {
            localDataSource.save(movie)
        }
    }
}