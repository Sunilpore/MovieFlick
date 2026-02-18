package com.movieflick.ui.feed

import android.content.res.Configuration
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.movieflick.di.entities.MovieListItem
import com.movieflick.ui.main.MainRouter
import com.movieflick.ui.theme.Dark
import com.movieflick.ui.theme.Light
import com.movieflick.ui.widget.LoaderFullScreen
import com.movieflick.ui.widget.MovieList
import com.movieflick.utils.preview.PreviewContainer
import kotlinx.coroutines.flow.flowOf


@Composable
fun FeedPage(
    mainRouter: MainRouter,
){

    /*FeedScreen(
        movies =
    )*/

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