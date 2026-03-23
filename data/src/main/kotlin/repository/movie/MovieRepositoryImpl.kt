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

    LogUtils.d("MovieRepositoryImpl_movies...")

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

}