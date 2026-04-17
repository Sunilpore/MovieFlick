package com.movieflick.ui.moviedetails

import com.movieflick.ui.base.BaseViewModel
import com.movieflick.utils.orFalse
import dagger.hilt.android.lifecycle.HiltViewModel
import entities.MovieEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import usecase.AddMovieToFavorite
import usecase.CheckFavoriteStatus
import usecase.GetMovieDetails
import usecase.RemoveMovieFromFavorite
import utils.asSuccessOrNull
import utils.onSuccess
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    private val checkFavStatus: CheckFavoriteStatus,
    private val addMovieToFavorite: AddMovieToFavorite,
    private val removeMovieFromFavorite: RemoveMovieFromFavorite,
    movieDetailsBundle: MovieDetailsBundle
) : BaseViewModel() {


    private val _uiState : MutableStateFlow<MovieDetailsState> = MutableStateFlow(MovieDetailsState())
    val uiState = _uiState.asStateFlow()

    private val movieId:Int = movieDetailsBundle.movieId

    init {
        onInitialState()
    }


    private fun onInitialState() = launch {

        val isFavorite = async { checkFavoriteStatus(movieId).asSuccessOrNull().orFalse() }
        getMovieById(movieId).onSuccess {
            _uiState.value = MovieDetailsState(
                title = it.title,
                description = it.description,
                imageUrl = it.backgroundUrl,
                isFavorite = isFavorite.await()
            )
        }
    }

    fun onFavoriteClicked() = launch {
        checkFavoriteStatus(movieId).onSuccess { isFavorite ->
            if(isFavorite) removeMovieFromFavorite(movieId) else addMovieToFavorite(movieId)
            _uiState.update { it.copy(isFavorite = !isFavorite) }
        }
    }


    private suspend fun getMovieById(movieId: Int): utils.Result<MovieEntity> = getMovieDetails(movieId)

    private suspend fun checkFavoriteStatus(movieId: Int): utils.Result<Boolean> = checkFavStatus.invoke(movieId)

}