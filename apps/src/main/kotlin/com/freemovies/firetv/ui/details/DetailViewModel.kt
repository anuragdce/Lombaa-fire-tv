package com.freemovies.firetv.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freemovies.firetv.data.MoviesRepository
import com.freemovies.firetv.data.local.model.Movie
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
                detailInfo.value = MovieDetail(posterUrl = movie.posterUrl)
                currentMovie = movie
            }
        }
    }
}