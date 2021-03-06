package com.freemovies.firetv.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freemovies.firetv.base.asMutable
import com.freemovies.firetv.data.MoviesRepository
import com.freemovies.firetv.data.local.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class VideoViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    val currentMovie: LiveData<Movie> = MutableLiveData()

    fun loadDetails(movieId: String) {
        viewModelScope.launch {
            moviesRepository.getMovie(movieId)?.let { movie ->
                currentMovie.asMutable().value = movie
            }
        }
    }
}