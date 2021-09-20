package com.lombaa.firetv.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lombaa.firetv.base.asMutable
import com.lombaa.firetv.base.extension.DATE_FORMAT
import com.lombaa.firetv.base.extension.print
import com.lombaa.firetv.data.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val quotesRepository: QuotesRepository
) : ViewModel() {
    val histories: LiveData<List<HistoryItem>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val historyItems = quotesRepository.getHistory().map { HistoryItem(it.text + "\n" + it.dateTime.print(DATE_FORMAT)) }
            histories.asMutable().value = historyItems
        }
    }
}