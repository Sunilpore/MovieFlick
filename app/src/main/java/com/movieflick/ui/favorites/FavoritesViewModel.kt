package com.movieflick.ui.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movieflick.di.entities.MovieListItem
import com.movieflick.mapper.toMovieListItem
import com.movieflick.ui.base.BaseViewModel
import com.movieflick.utils.singleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import usecase.GetFavoriteMovies
import javax.inject.Inject

/**
 * Created by Sunil_P on 01/04/2026
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoriteMovies: GetFavoriteMovies
): BaseViewModel() {

    val movies: Flow<PagingData<MovieListItem>> = getFavoriteMovies(30)
        .map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovieListItem()
            }
        }.cachedIn(viewModelScope)


    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationState: MutableSharedFlow<FavoritesNavigationState> = singleSharedFlow()
    val navigationState = _navigationState.asSharedFlow()

    fun onMovieClicked(movieId: Int) =
        _navigationState.tryEmit(FavoritesNavigationState.MovieDetails(movieId))

    fun onLoadStateUpdate(loadState: CombinedLoadStates, itemCount: Int){

        val showLoading = loadState.refresh is LoadState.Loading
        val showNoData = loadState.append.endOfPaginationReached && itemCount <1

        _uiState.update {
            it.copy(
                isLoading = showLoading,
                noDataAvailable = showNoData
            )
        }
    }


}