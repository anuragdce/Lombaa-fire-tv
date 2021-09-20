package com.lombaa.firetv.data.local

import androidx.annotation.WorkerThread
import com.lombaa.firetv.data.local.model.Quote
import org.joda.time.DateTime

interface LocalDataSource {
    @WorkerThread
    suspend fun save(quote: Quote)

    @WorkerThread
    suspend fun getQuote(dateTime: DateTime): Quote?

    @WorkerThread
    suspend fun getHistory(): List<Quote>

    @WorkerThread
    suspend fun getLatestQuote(): Quote?
}