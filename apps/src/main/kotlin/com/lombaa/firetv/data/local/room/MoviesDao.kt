package com.lombaa.firetv.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lombaa.firetv.data.local.room.model.RoomMovie

@Dao
internal interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<RoomMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: RoomMovie)

    @Query("SELECT * FROM movies WHERE id =:id")
    suspend fun getMovie(id: String): RoomMovie?

    @Query("SELECT * FROM movies WHERE id in (:ids) ORDER BY releaseDate DESC")
    suspend fun getMovies(ids: List<String>): List<RoomMovie>

    @Query("SELECT * FROM movies ORDER BY releaseDate DESC")
    suspend fun getMovies(): List<RoomMovie>

    @Query("DELETE FROM movies")
    suspend fun delete()
}