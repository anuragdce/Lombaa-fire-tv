package com.lombaa.firetv.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lombaa.firetv.base.asMutable
import com.lombaa.firetv.base.lifecycle.SingleLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {
    val viewModeType: LiveData<ViewMode> = SingleLiveData()

    fun setViewMode(viewMode: ViewMode) {
        viewModeType.asMutable().value = viewMode
    }
}

