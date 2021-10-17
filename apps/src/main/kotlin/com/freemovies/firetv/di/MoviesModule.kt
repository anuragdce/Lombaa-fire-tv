package com.freemovies.firetv.di

import android.content.Context
import androidx.room.Room
import com.fasterxml.jackson.databind.ObjectMapper
import com.freemovies.firetv.data.DefaultMoviesRepository
import com.freemovies.firetv.data.MoviesRepository
import com.freemovies.firetv.data.local.LocalDataSource
import com.freemovies.firetv.data.local.room.MoviesDatabase
import com.freemovies.firetv.data.local.room.model.RoomLocalDataSource
import com.freemovies.firetv.data.remote.DefaultRemoteDataSource
import com.freemovies.firetv.data.remote.MoviesApi
import com.freemovies.firetv.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesModule {
    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideLocalDataSource(moviesDatabase: MoviesDatabase): LocalDataSource =
        RoomLocalDataSource(moviesDatabase.moviesDao(), moviesDatabase.tagsDao())

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addInterceptor(logging)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideNewsCoApi(objectMapper: ObjectMapper, okHttpClient: OkHttpClient): MoviesApi {
        val httpUrl = "http://api.unreel.me"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .baseUrl(httpUrl).client(okHttpClient).build()
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDatasource(@ApplicationContext context: Context, moviesApi: MoviesApi, objectMapper: ObjectMapper):
            RemoteDataSource = DefaultRemoteDataSource(moviesApi, context.assets, objectMapper)

    @Singleton
    @Provides
    fun provideQuotesRepository(
        @ApplicationContext context: Context,
        localDatasource: LocalDataSource,
        remoteDatasource: RemoteDataSource
    ): MoviesRepository = DefaultMoviesRepository(context, localDatasource, remoteDatasource)
}