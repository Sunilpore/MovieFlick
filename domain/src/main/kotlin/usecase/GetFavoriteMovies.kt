package usecase

import repository.MovieRepository


/**
 * Created By Sunil_P on 31/03/2026
 */


class GetFavoriteMovies (
    private val movieRepository: MovieRepository
) {
    operator fun invoke(pageSize: Int) = movieRepository.favoriteMovies(pageSize)
}