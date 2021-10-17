package com.lombaa.firetv.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lombaa.firetv.data.local.room.model.RoomTag
import com.lombaa.firetv.data.local.room.model.RoomTagMovieMap

@Dao
internal interface TagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tags: List<RoomTag>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: RoomTag)

    @Query("DELETE from tags")
    suspend fun deleteTags()

    @Query("SELECT * FROM tags")
    suspend fun getTags(): List<RoomTag>

    @Query("SELECT movieId FROM movies_tags_map WHERE tag = :tag")
    suspend fun getMovieIds(tag: String): List<String>

    @Query("DELETE from movies_tags_map")
    suspend fun deleteMoviesTagsMap()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapping(maps: List<RoomTagMovieMap>)
}