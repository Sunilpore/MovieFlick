package com.movieflick.ui.feed

import android.content.res.Configuration
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.movieflick.di.entities.MovieListItem
import com.movieflick.navigation.Page
import com.movieflick.ui.main.MainRouter
import com.movieflick.ui.navigationbar.NavigationBarSharedViewModel
import com.movieflick.ui.theme.Dark
import com.movieflick.ui.theme.Light
import com.movieflick.ui.widget.LoaderFullScreen
import com.movieflick.ui.widget.MovieList
import com.movieflick.ui.widget.PullToRefresh
import com.movieflick.utils.collectAsEffect
import com.movieflick.utils.preview.PreviewContainer
import kotlinx.coroutines.flow.flowOf


@Composable
fun FeedPage(
    mainRouter: MainRouter,
    viewModel: FeedViewModel,
    sharedViewModel: NavigationBarSharedViewModel
){
    val moviesPaging = viewModel.movies.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()
    val lazyGridState = rememberLazyGridState()

    viewModel.navigationState.collectAsEffect { navigationState ->
        when(navigationState){
            is FeedNavigationState.MovieDetails -> mainRouter.navigateToMovieDetails(navigationState.movieId)
        }
    }

    viewModel.refreshListState.collectAsEffect {
        moviesPaging.refresh()
    }

    sharedViewModel.bottomItem.collectAsEffect {
        if(it.page == Page.Feed){
            lazyGridState.animateScrollToItem(0)
        }
    }

    LaunchedEffect(key1 = moviesPaging.loadState) {
        viewModel.onLoadStateUpdate(moviesPaging.loadState)
    }

    PullToRefresh(refresh = uiState.showLoading) {
        FeedScreen(
            movies = moviesPaging,
            uiState = uiState,
            lazyGridState = lazyGridState,
            onMovieClick = viewModel::onMovieClicked
        )
    }

}


@Composable
private fun FeedScreen(
    movies: LazyPagingItems<MovieListItem>,
    uiState: FeedUiState,
    lazyGridState : LazyGridState,
    onMovieClick : (movieId:Int) -> Unit
){
    Surface{
        if(uiState.showLoading){
            LoaderFullScreen()
        } else {
            MovieList(movies, onMovieClick, lazyGridState)
        }
    }
}


//-----------------------------------------------------------------//
//Previews


@Preview(Light)
@Preview(Dark, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FeedScreenPreview(){

    val movies = flowOf(
        PagingData.from(
            listOf<MovieListItem>(
                MovieListItem.Movie(1, "",""),
                MovieListItem.Movie(2, "",""),
                MovieListItem.Movie(3, "",""),
                MovieListItem.Movie(4, "",""),
                MovieListItem.Movie(5, "",""),
            )
        )
    ).collectAsLazyPagingItems()

    PreviewContainer {
        FeedScreen(
            movies = movies,
            uiState = FeedUiState(
                showLoading = false,
                errorMessage = null
            ),
            lazyGridState = rememberLazyGridState(),
            onMovieClick = {}
        )
    }
}