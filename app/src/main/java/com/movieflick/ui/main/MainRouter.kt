package com.movieflick.ui.main

import androidx.navigation.NavHostController
import com.movieflick.navigation.Page

class MainRouter(
    private val mainNavController : NavHostController
) {

    fun navigateToSearch(){
        mainNavController.navigate(Page.Search)
    }

    fun navigateToMovieDetails(movieId: Int){
        mainNavController.navigate(Page.MovieDetails(movieId = movieId))
    }

}