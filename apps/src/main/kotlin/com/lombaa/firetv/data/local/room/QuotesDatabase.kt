package com.lombaa.firetv.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lombaa.firetv.base.room.BaseConverters
import com.lombaa.firetv.data.local.room.model.RoomQuote

@Database(
    entities = [RoomQuote::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BaseConverters::class)
internal abstract class QuotesDatabase : RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
}