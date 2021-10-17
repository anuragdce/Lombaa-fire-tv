package com.lombaa.firetv.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class RoomTag(@PrimaryKey val name: String)