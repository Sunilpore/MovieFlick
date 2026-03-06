package com.movieflick.ui.feed.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.movieflick.di.entities.MovieListItem
import com.movieflick.mapper.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repository.MovieRepository
import javax.inject.Inject

/**
 * @author by Sunil_P on 23/02/2026
 */
class GetMoviesWithSeparators @Inject constructor(
    private val movieRepository: MovieRepository,
    private val insertSeparatorIntoPagingData: InsertSeparatorIntoPagingData
) {

    fun movies(pageSize: Int): Flow<PagingData<MovieListItem>> = movieRepository.movies(pageSize).map {
        val pagingData: PagingData<MovieListItem.Movie> = it.map { movie -> movie.toPresentation() }
        insertSeparatorIntoPagingData.insert(pagingData)
    }
}