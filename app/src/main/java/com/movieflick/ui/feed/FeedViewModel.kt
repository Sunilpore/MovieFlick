package com.movieflick.ui.feed

import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.movieflick.di.entities.MovieListItem
import com.movieflick.ui.base.BaseViewModel
import com.movieflick.ui.feed.usecase.GetMoviesWithSeparators
import com.movieflick.utils.singleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import utils.NetworkMonitor
import javax.inject.Inject

/**
 * Created by Sunil_P on 20/02/2026
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    val networkMonitor: NetworkMonitor,
    getMoviesWithSeparators: GetMoviesWithSeparators
): BaseViewModel() {

    val movies: Flow<PagingData<MovieListItem>> = flowOf(PagingData.from(listOf<MovieListItem>(MovieListItem.Movie(1, "",""))
    ))
    /*getMoviesWithSeparators.movies(
        pageSize = 90
    ).cachedIn(viewModelScope)*/

    private val _uiState: MutableStateFlow<FeedUiState> = MutableStateFlow(FeedUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationState: MutableSharedFlow<FeedNavigationState> = singleSharedFlow()
    val navigationState = _navigationState.asSharedFlow()

    private val _refreshListState: MutableSharedFlow<Unit> = singleSharedFlow()
    val refreshListState = _refreshListState.asSharedFlow()

    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus(){
        networkMonitor.networkState
            .onEach { if(it.shouldRefresh) onRefresh()}
            .launchIn(viewModelScope)

    }

    fun onMovieClicked(movieId: Int) =
        _navigationState.tryEmit(FeedNavigationState.MovieDetails(movieId))

    fun onLoadStateUpdate(loadState: CombinedLoadStates){
        val showLoading = loadState.refresh is LoadState.Loading

        val error = when(val refresh = loadState.refresh){
            is LoadState.Error -> refresh.error.message
            else -> null
        }

        _uiState.update { it.copy(showLoading = showLoading, errorMessage = error) }
    }


    fun onRefresh() = launch {
        _refreshListState.emit(Unit)
    }


}