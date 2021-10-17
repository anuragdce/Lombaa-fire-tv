package com.lombaa.firetv.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_tags_map")
data class RoomTagMovieMap(@PrimaryKey(autoGenerate = true) val id: Int = 0, val tag: String, val movieId: String)