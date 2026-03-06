package com.movieflick.mapper

import com.movieflick.di.entities.MovieListItem
import entities.MovieEntity


/**
 * Created By Sunil_P on 23/02/2026
 */
fun MovieEntity.toPresentation() = MovieListItem.Movie(
    id = id,
    imgUrl = image,
    category = category
)

fun MovieEntity.toMovieListItem(): MovieListItem = this.toPresentation()