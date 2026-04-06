package com.movieflick.ui.favorites

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.movieflick.R
import com.movieflick.di.entities.MovieListItem
import com.movieflick.ui.main.MainRouter
import com.movieflick.ui.theme.Dark
import com.movieflick.ui.theme.Light
import com.movieflick.ui.widget.EmptyStateIcon
import com.movieflick.ui.widget.EmptyStateView
import com.movieflick.ui.widget.LoaderFullScreen
import com.movieflick.ui.widget.MovieList
import com.movieflick.utils.collectAsEffect
import com.movieflick.utils.preview.PreviewContainer
import kotlinx.coroutines.flow.flowOf


@Composable
fun FavoritesPage(
    mainRouter: MainRouter,
    viewModel: FavoritesViewModel
){

    val uiState by viewModel.uiState.collectAsState()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    viewModel.onLoadStateUpdate(movies.loadState, movies.itemCount)

    viewModel.navigationState.collectAsEffect { navigationState ->
        when(navigationState){
            is FavoritesNavigationState.MovieDetails -> mainRouter.navigateToMovieDetails(navigationState.movieId)
        }
    }

    FavoritesScreen(
        favoriteUiState = uiState,
        movies = movies,
        onMovieClick = viewModel::onMovieClicked
    )

}


@Composable
fun FavoritesScreen(
    favoriteUiState: FavoriteUiState,
    movies: LazyPagingItems<MovieListItem>,
    onMovieClick: (movieId: Int) -> Unit
){

    Surface {
        val isLoading = favoriteUiState.isLoading
        val noDataAvailable = favoriteUiState.noDataAvailable

        if(isLoading){
            LoaderFullScreen()
        } else {
            if(noDataAvailable){
                EmptyStateView(
                    modifier = Modifier.padding(16.dp),
                    icon = EmptyStateIcon(iconRes = R.drawable.bg_empty_favorite),
                    title = stringResource(id = R.string.no_favorite_movies_title),
                    subTitle = stringResource(id = R.string.no_favorite_movies_subtitle)
                )
            } else {
                MovieList(movies = movies, onMovieClick = onMovieClick)
            }
        }
    }
}


//--------------------------------------------------------------------------------------------//
//Preview//

@Preview(showSystemUi = true, name = Light)
@Preview(showSystemUi = true, name = Dark, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoriteScreenNoDataPreview(){

    val movieItems: List<MovieListItem> = listOf(MovieListItem.Movie(1, "image1.jpg","Action"))

    PreviewContainer {
        val movies = flowOf(PagingData.from(movieItems)).collectAsLazyPagingItems()
        FavoritesScreen(FavoriteUiState(isLoading = false, noDataAvailable = true), movies ){}
    }

}




