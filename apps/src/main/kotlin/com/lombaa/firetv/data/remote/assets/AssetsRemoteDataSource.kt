package com.lombaa.firetv.data.remote.assets

import android.content.res.AssetManager
import androidx.annotation.WorkerThread
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.lombaa.firetv.data.local.model.Quote
import com.lombaa.firetv.data.remote.RemoteDataSource
import com.lombaa.firetv.data.remote.model.RemoteQuote

class AssetsRemoteDataSource(private val assetManager: AssetManager, private val objectMapper: ObjectMapper) : RemoteDataSource {

    companion object {
        private const val FILE_NAME = "quotes.json"
    }

    @WorkerThread
    override suspend fun getQuote(latestQuote: Quote?): RemoteQuote {
        val stream = assetManager.open(FILE_NAME)
        val items: Array<String> = objectMapper.readValue(stream)
        return latestQuote?.let {
            val index = items.indexOf(it.text) + 1
            if (index >= items.size) {
                RemoteQuote(items.first())
            } else {
                RemoteQuote(items[index])
            }
        } ?: run { RemoteQuote(items.first()) }
    }
}