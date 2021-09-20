package com.lombaa.firetv.base.room

import androidx.room.TypeConverter
import java.util.*

class BaseConverters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}