package com.freemovies.firetv.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.freemovies.firetv.base.room.BaseConverters
import com.freemovies.firetv.data.local.room.model.RoomMovie
import com.freemovies.firetv.data.local.room.model.RoomTag
import com.freemovies.firetv.data.local.room.model.RoomTagMovieMap

@Database(
    entities = [RoomMovie::class, RoomTag::class, RoomTagMovieMap::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BaseConverters::class, RoomConverters::class)
internal abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tagsDao(): TagDao
}