package com.lombaa.firetv.di

import android.content.Context
import androidx.room.Room
import com.fasterxml.jackson.databind.ObjectMapper
import com.lombaa.firetv.data.DefaultQuoteRepository
import com.lombaa.firetv.data.QuotesRepository
import com.lombaa.firetv.data.local.LocalDataSource
import com.lombaa.firetv.data.local.room.QuotesDatabase
import com.lombaa.firetv.data.local.room.model.RoomLocalDataSource
import com.lombaa.firetv.data.remote.RemoteDataSource
import com.lombaa.firetv.data.remote.assets.AssetsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object QuotesModule {
    @Singleton
    @Provides
    fun provideQuotesDatabase(@ApplicationContext context: Context): QuotesDatabase =
        Room.databaseBuilder(context, QuotesDatabase::class.java, "quotes.db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideLocalDataSource(quotesDatabase: QuotesDatabase): LocalDataSource =
        RoomLocalDataSource(quotesDatabase.quotesDao())

    @Singleton
    @Provides
    fun provideRemoteDatasource(@ApplicationContext context: Context, objectMapper: ObjectMapper):
            RemoteDataSource = AssetsRemoteDataSource(context.assets, objectMapper)

    @Singleton
    @Provides
    fun provideQuotesRepository(
        localDatasource: LocalDataSource,
        remoteDatasource: RemoteDataSource
    ): QuotesRepository = DefaultQuoteRepository(localDatasource, remoteDatasource)
}