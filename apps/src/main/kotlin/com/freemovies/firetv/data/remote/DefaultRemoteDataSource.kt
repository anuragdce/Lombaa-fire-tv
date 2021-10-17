package com.freemovies.firetv.data.remote

import android.content.res.AssetManager
import androidx.annotation.WorkerThread
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.freemovies.firetv.base.data.local.model.RequestResult
import com.freemovies.firetv.data.local.model.Movie
import com.freemovies.firetv.data.remote.model.RemoteMovie
import retrofit2.HttpException

internal class DefaultRemoteDataSource(
    private val moviesApi: MoviesApi,
    private val assetManager: AssetManager,
    private val objectMapper: ObjectMapper
) : RemoteDataSource {

    companion object {
        private const val FILE_NAME = "tags.json"
    }

    @WorkerThread
    override suspend fun getMovies(): RequestResult<List<Movie>> {
        return try {
            val response = moviesApi.download()
            if (response.isSuccessful) {
                val jsonNode = objectMapper.readTree(response.body()!!.byteStream())
                val movies: List<RemoteMovie> = objectMapper.readValue(jsonNode.get("movies").traverse())
                RequestResult.Success(movies.map { it.map() })
            } else {
                throw HttpException(response)
            }
        } catch (exception: Exception) {
            RequestResult.Error(exception)
        }
    }

    @WorkerThread
    override suspend fun getTags(): List<String> {
        val stream = assetManager.open(FILE_NAME)
        val items: Array<String> = objectMapper.readValue(stream)
        return items.toList()
    }
}