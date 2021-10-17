package com.lombaa.firetv.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lombaa.firetv.data.MoviesRepository
import com.lombaa.firetv.data.local.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    val detailInfo = MutableLiveData(MovieDetail())
    private var currentMovie: Movie? = null

    fun loadDetails(movieId: String) {
        viewModelScope.launch {
            moviesRepository.getMovie(movieId)?.let { movie ->
                detailInfo.value = MovieDetail((movie.posterUrl))
                currentMovie = movie
            }
        }
    }
}