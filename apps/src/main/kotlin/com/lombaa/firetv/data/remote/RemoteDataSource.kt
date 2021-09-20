package com.lombaa.firetv.data.remote

import androidx.annotation.WorkerThread
import com.lombaa.firetv.data.local.model.Quote
import com.lombaa.firetv.data.remote.model.RemoteQuote

interface RemoteDataSource {
    @WorkerThread
    suspend fun getQuote(latestQuote: Quote?): RemoteQuote
}