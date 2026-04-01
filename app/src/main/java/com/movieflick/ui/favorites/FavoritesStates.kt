package com.movieflick.ui.favorites

data class FavoriteUiState (
    val isLoading: Boolean = false,
    val noDataAvailable: Boolean = false
)


sealed class FavoritesNavigationState {
    data class MovieDetails(val movieId:Int): FavoritesNavigationState()
}
