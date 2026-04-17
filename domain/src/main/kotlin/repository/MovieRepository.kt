package repository

import androidx.paging.PagingData
import entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import utils.Result


/**
 * Created By Sunil_P on 18/02/2026
 */

interface MovieRepository {
    fun movies(pageSize: Int): Flow<PagingData<MovieEntity>>
    fun favoriteMovies(pageSize: Int): Flow<PagingData<MovieEntity>>

    suspend fun getMovie(movieId: Int): Result<MovieEntity>
    suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    suspend fun addMovieToFavorite(movieId: Int)
    suspend fun removeMovieFromFavorite(movieId: Int)

}