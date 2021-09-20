package com.lombaa.firetv.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lombaa.firetv.base.data.Mappable
import com.lombaa.firetv.base.data.MappableFrom
import com.lombaa.firetv.base.extension.DATE_FORMAT
import com.lombaa.firetv.base.extension.print
import com.lombaa.firetv.base.extension.toDateTime
import com.lombaa.firetv.data.local.model.Quote
import org.joda.time.DateTime

@Entity(tableName = "quotes")
internal data class RoomQuote(
    @PrimaryKey
    @ColumnInfo(name = "date") val date: String,
    val quote: String,
    val timeCreated: Long
) : Mappable<Quote> {

    override fun map(): Quote = Quote(quote, date.toDateTime(DATE_FORMAT), DateTime(timeCreated))

    companion object : MappableFrom<Quote, RoomQuote> {

        override fun mapFrom(input: Quote): RoomQuote =
            RoomQuote(input.dateTime.print(DATE_FORMAT), input.text, input.timeCreated.millis)
    }
}
