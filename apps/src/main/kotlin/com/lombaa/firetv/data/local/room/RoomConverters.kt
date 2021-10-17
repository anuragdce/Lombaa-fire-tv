package com.lombaa.firetv.data.local.room

import androidx.room.TypeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.lombaa.firetv.data.local.model.Credit

class RoomConverters {

    private val objectMapper = ObjectMapper().apply {
        registerModule(KotlinModule())
    }

    @TypeConverter
    fun stringToList(value: String?): List<String>? {
        return value?.let { objectMapper.readValue(it) }
    }

    @TypeConverter
    fun listToString(items: List<String>): String {
        return objectMapper.writeValueAsString(items)
    }

    @TypeConverter
    fun stringToCredit(value: String?): List<Credit>? {
        return value?.let { objectMapper.readValue(it) }
    }

    @TypeConverter
    fun creditsToString(items: List<Credit>): String {
        return objectMapper.writeValueAsString(items)
    }
}