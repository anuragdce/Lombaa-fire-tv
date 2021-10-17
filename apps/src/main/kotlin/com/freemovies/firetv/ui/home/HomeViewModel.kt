package com.freemovies.firetv.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freemovies.firetv.data.MoviesRepository
import com.freemovies.firetv.data.local.model.PlayList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    val carousels = MutableLiveData<List<PlayList>>()

    init {
        viewModelScope.launch {
            val items = moviesRepository.getPlayList()
            carousels.value = items
        }
    }
}