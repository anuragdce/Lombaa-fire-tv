package com.lombaa.firetv.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lombaa.firetv.base.asMutable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
  application: Application,
) : AndroidViewModel(application) {
  val histories: LiveData<List<HistoryItem>> = MutableLiveData()

  init {
    viewModelScope.launch {
      val historyItems = mutableListOf<HistoryItem>()
      historyItems.add(HistoryItem("History 1"))
      historyItems.add(HistoryItem("History 2"))
      historyItems.add(HistoryItem("History 3"))
      historyItems.add(HistoryItem("History 4"))
      historyItems.add(HistoryItem("History 5"))
      historyItems.add(HistoryItem("History 6"))
      historyItems.add(HistoryItem("History 7"))
      historyItems.add(HistoryItem("History 7"))
      historyItems.add(HistoryItem("History 8"))
      historyItems.add(HistoryItem("History 9"))
      histories.asMutable().value = historyItems;
    }
  }
}