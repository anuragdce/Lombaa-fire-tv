package com.lombaa.firetv.ui.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ContactViewModel @Inject constructor(
  application: Application,
) : AndroidViewModel(application) {

  init {
    viewModelScope.launch {

    }
  }
}