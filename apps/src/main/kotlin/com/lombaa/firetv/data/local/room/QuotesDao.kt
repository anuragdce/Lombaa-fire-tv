package com.lombaa.firetv.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lombaa.firetv.data.local.room.model.RoomQuote

@Dao
internal interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<RoomQuote>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: RoomQuote): Long

    @Query("SELECT * FROM quotes ORDER BY timeCreated DESC")
    suspend fun getAllItems(): List<RoomQuote>

    @Query("SELECT * FROM quotes ORDER BY timeCreated DESC LIMIT 1")
    suspend fun getLatestQuote(): RoomQuote?

    @Query("SELECT * FROM quotes WHERE date =:date")
    suspend fun getQuote(date: String): RoomQuote?
}