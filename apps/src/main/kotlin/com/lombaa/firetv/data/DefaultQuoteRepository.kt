package com.lombaa.firetv.data

import com.lombaa.firetv.base.extension.DATE_FORMAT
import com.lombaa.firetv.base.extension.print
import com.lombaa.firetv.data.local.LocalDataSource
import com.lombaa.firetv.data.local.model.Quote
import com.lombaa.firetv.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class DefaultQuoteRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : QuotesRepository {

    override suspend fun getTodayQuote(): Quote {
        return withContext(Dispatchers.IO) {
            val currentTime = DateTime.now()
            val currentQuote = localDataSource.getQuote(currentTime)
            if (currentQuote != null) {
                currentQuote
            } else {
                val latestQuote = localDataSource.getLatestQuote()
                val remoteQuote = remoteDataSource.getQuote(latestQuote)
                val quote = Quote(remoteQuote.text, currentTime)
                localDataSource.save(quote)
                quote
            }
        }
    }

    override suspend fun getHistory(): List<Quote> {
        return withContext(Dispatchers.IO) {
            val currentTime = DateTime.now().print(DATE_FORMAT)
            localDataSource.getHistory().filter { it.dateTime.print(DATE_FORMAT) != currentTime }
        }
    }
}