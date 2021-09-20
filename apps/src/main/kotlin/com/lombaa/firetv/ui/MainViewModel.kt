package com.lombaa.firetv.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lombaa.firetv.base.lifecycle.SingleLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
  application: Application,
) : AndroidViewModel(application) {
  val navigateType: LiveData<FragmentType> = SingleLiveData()

  enum class FragmentType {
    Home,
    History,
    ContactUs
  }
}

