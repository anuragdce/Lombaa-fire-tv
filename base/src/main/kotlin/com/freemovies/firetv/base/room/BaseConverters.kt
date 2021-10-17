package com.freemovies.firetv.base.room

import androidx.room.TypeConverter
import org.joda.time.DateTime
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

    @TypeConverter
    fun timestampToDateTime(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    fun dateTimeToTimestamp(date: DateTime?): Long? {
        return date?.millis
    }
}