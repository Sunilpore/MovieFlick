package repository

import androidx.paging.PagingData
import entities.MovieEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created By Sunil_P on 18/02/2026
 */

interface MovieRepository {
    fun movies(pageSize: Int): Flow<PagingData<MovieEntity>>
    fun favoriteMovies(pageSize: Int): Flow<PagingData<MovieEntity>>

}