package com.freemovies.firetv.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freemovies.firetv.base.asMutable
import com.freemovies.firetv.base.lifecycle.SingleLiveData
import com.freemovies.firetv.data.MoviesRepository
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