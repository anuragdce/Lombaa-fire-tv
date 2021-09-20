package com.lombaa.firetv.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lombaa.firetv.data.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val quotesRepository: QuotesRepository
) : ViewModel() {

    val quote = MutableLiveData("")

    init {
        viewModelScope.launch {
            quote.value = quotesRepository.getTodayQuote().text
        }
    }
}