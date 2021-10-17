package com.lombaa.firetv.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lombaa.firetv.base.asMutable
import com.lombaa.firetv.base.lifecycle.SingleLiveData
import com.lombaa.firetv.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val dismiss: LiveData<Unit> = SingleLiveData()

    init {
        viewModelScope.launch {
            val response = moviesRepository.load()
            dismiss.asMutable().value = Unit
        }
    }
}