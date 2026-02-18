package com.movieflick.di.entities



sealed class MovieListItem {

    data class Movie (
        val id:Int,
        val imgUrl: String,
        val category: String,
    ) : MovieListItem()

    data class Separator(val category:String): MovieListItem()

}