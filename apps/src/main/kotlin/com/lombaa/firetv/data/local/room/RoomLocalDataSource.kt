package com.lombaa.firetv.data.local.room.model

import androidx.annotation.WorkerThread
import com.lombaa.firetv.data.local.LocalDataSource
import com.lombaa.firetv.data.local.model.Movie
import com.lombaa.firetv.data.local.room.MoviesDao
import com.lombaa.firetv.data.local.room.TagDao

internal class RoomLocalDataSource(
    private val moviesDao: MoviesDao,
    private val tagsDao: TagDao,
) : LocalDataSource {

    @WorkerThread
    override suspend fun save(movie: Movie) {
        moviesDao.insert(RoomMovie.mapFrom(movie))
    }

    @WorkerThread
    override suspend fun save(movies: List<Movie>) {
        tagsDao.deleteTags()
        moviesDao.delete()
        tagsDao.deleteMoviesTagsMap()
        movies.forEach { movie ->
            val tags = movie.tags.filter { it.first().isUpperCase() }.map { RoomTag(it) }
            val tagMoviesMap = tags.map { RoomTagMovieMap(tag = it.name, movieId = movie.id) }
            val progress = moviesDao.getMovie(movie.id)?.progress ?: 0
            moviesDao.insert(RoomMovie.mapFrom(movie.copy(progress = progress)))
            tagsDao.insert(tags)
            tagsDao.insertMapping(tagMoviesMap)
        }
    }

    @WorkerThread
    override suspend fun getMovies(tag: String): List<Movie> {
        val movieIds = tagsDao.getMovieIds(tag)
        return moviesDao.getMovies(movieIds).map { it.map() }
    }

    @WorkerThread
    override suspend fun getMovies(): List<Movie> {
        return moviesDao.getMovies().map { it.map() }
    }

    @WorkerThread
    override suspend fun getMovie(id: String): Movie? {
        return moviesDao.getMovie(id)?.map()
    }
}