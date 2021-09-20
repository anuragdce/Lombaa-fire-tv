package com.lombaa.firetv.base.extension

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.util.*

const val DATE_FORMAT = "MM-dd-yyyy"

fun Date.print(format: String): String {
  val dateFormat = DateTimeFormat.forPattern(format)
  return dateFormat.print(time)
}

fun DateTime.print(format: String): String {
  val dateFormat = DateTimeFormat.forPattern(format)
  return dateFormat.print(this)
}

fun currentDate(): Date {
  return DateTime.now(DateTimeZone.UTC).toDate()
}

