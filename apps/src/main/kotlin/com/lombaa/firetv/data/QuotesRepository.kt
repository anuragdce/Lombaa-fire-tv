package com.lombaa.firetv.data

import com.lombaa.firetv.data.local.model.Quote

interface QuotesRepository {
    suspend fun getTodayQuote(): Quote

    suspend fun getHistory(): List<Quote>
}