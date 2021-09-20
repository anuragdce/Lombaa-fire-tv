package com.lombaa.firetv.ui.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ContactViewModel @Inject constructor(
  application: Application,
) : AndroidViewModel(application) {

  val email: LiveData<String> = MutableLiveData("abc@gmail.com")
  val phone: LiveData<String> = MutableLiveData("+91 9876543210")
  val address: LiveData<String> = MutableLiveData("Colaborar Labs, Noida One Building, Sector 62, Noida.")

  init {
    viewModelScope.launch {

    }
  }
}