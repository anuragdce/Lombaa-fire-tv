package com.lombaa.firetv.data.local.room.model

import androidx.annotation.WorkerThread
import com.lombaa.firetv.base.extension.DATE_FORMAT
import com.lombaa.firetv.base.extension.print
import com.lombaa.firetv.data.local.LocalDataSource
import com.lombaa.firetv.data.local.model.Quote
import com.lombaa.firetv.data.local.room.QuotesDao
import org.joda.time.DateTime

internal class RoomLocalDataSource(private val quotesDao: QuotesDao) : LocalDataSource {
    @WorkerThread
    override suspend fun save(quote: Quote) {
        quotesDao.insert(RoomQuote.mapFrom(quote))
    }

    @WorkerThread
    override suspend fun getQuote(dateTime: DateTime): Quote? {
        return quotesDao.getQuote(dateTime.print(DATE_FORMAT))?.map()
    }

    @WorkerThread
    override suspend fun getHistory(): List<Quote> {
        return quotesDao.getAllItems().map { it.map() }
    }

    override suspend fun getLatestQuote(): Quote? {
        return quotesDao.getLatestQuote()?.map()
    }
}