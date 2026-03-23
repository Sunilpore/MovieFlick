package repository.movie.favorite

import androidx.paging.PagingSource
import db.favoritemovies.FavoriteMovieDao
import entities.FavoriteMovieDbData
import entities.MovieDbData
import exception.DataNotAvailableException
import utils.Result

class FavoriteMoviesLocalDataSource(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteMoviesDataSource.Local {

    override fun favoriteMovies(): PagingSource<Int, MovieDbData> = favoriteMovieDao.favoriteMovies()

    override suspend fun getFavoriteMovieIds(): Result<List<Int>> {
        val movieIds = favoriteMovieDao.getAll().map { it.movieId }

        return if(movieIds.isEmpty()){
            Result.Success(movieIds)
        } else {
            Result.Error(DataNotAvailableException())
        }

    }

    override suspend fun addMovieToFavorite(movieId: Int) {
        favoriteMovieDao.add(FavoriteMovieDbData(movieId))
    }

    override suspend fun removeMovieFromFavorite(movieId: Int) {
        favoriteMovieDao.remove(movieId)
    }

    override suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean> {
        return Result.Success(favoriteMovieDao.get(movieId) != null)
    }


}