package repository.movie

import androidx.paging.PagingSource
import db.movies.MovieDao
import db.movies.MovieRemoteKeyDao
import entities.MovieData
import entities.MovieDbData
import entities.MovieEntity
import entities.MovieRemoteKeyDbData
import entities.toDbData
import entities.toDomain
import exception.DataNotAvailableException
import utils.Result


/**
 * Created By Sunil_P on 17/03/2026
 */
class MovieLocalDataSource(
    private val movieDao: MovieDao,
    private val remoteKeyDao: MovieRemoteKeyDao,
) : MovieDataSource.Local {


    override fun movies(): PagingSource<Int, MovieDbData> = movieDao.movies()

    override suspend fun getMovies(): Result<List<MovieEntity>> {
        val movies = movieDao.getMovies()
        return if(movies.isNotEmpty()){
            Result.Success(movies.map { it.toDomain()})
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getMovie(movieId: Int): Result<MovieEntity> {
        return movieDao.getMovie(movieId)?.let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException())
    }

    override suspend fun saveMovies(movies: List<MovieData>) {
        movieDao.saveMovies(movies.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): MovieRemoteKeyDbData? {
        return remoteKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: MovieRemoteKeyDbData) {
        remoteKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearMovies() {
        movieDao.clearMoviesExceptFavorites()
    }

    override suspend fun clearRemoteKeys() {
        remoteKeyDao.clearRemoteKeys()
    }

}