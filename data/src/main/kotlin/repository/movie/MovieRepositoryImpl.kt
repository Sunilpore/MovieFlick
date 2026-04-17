package repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import entities.MovieEntity
import entities.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repository.MovieRepository
import repository.movie.favorite.FavoriteMoviesDataSource
import utils.Result
import utils.map
import utils.onError
import utils.onSuccess


/**
 * Created By Sunil_P on 18/02/2026
 */

class MovieRepositoryImpl (
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local,
    private val remoteMediator: MovieRemoteMediator,
    private val localFavorite: FavoriteMoviesDataSource.Local
): MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun movies(pageSize: Int): Flow<PagingData<MovieEntity>> {

    return Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        remoteMediator = remoteMediator,
        pagingSourceFactory = { local.movies() }
    ).flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }
    }

    override fun favoriteMovies(pageSize: Int): Flow<PagingData<MovieEntity>> = Pager (
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { localFavorite.favoriteMovies()}
    ).flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }

    override suspend fun getMovie(movieId: Int): Result<MovieEntity> {
        return when (val localResult = local.getMovie(movieId)){
            is Result.Success -> localResult
            is Result.Error -> remote.getMovie(movieId).map { it.toDomain() }
        }
    }

    override suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean> = localFavorite.checkFavoriteStatus(movieId)

    override suspend fun addMovieToFavorite(movieId: Int) {
        local.getMovie(movieId)
            .onSuccess {
                localFavorite.addMovieToFavorite(movieId)
            }
            .onError {
                remote.getMovie(movieId).onSuccess {  movie ->
                    local.saveMovies(listOf(movie))
                    localFavorite.addMovieToFavorite(movieId)
                }
            }
    }

    override suspend fun removeMovieFromFavorite(movieId: Int) = localFavorite.removeMovieFromFavorite(movieId)


}