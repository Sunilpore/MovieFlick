package usecase

import repository.MovieRepository

/**
 * Created By Sunil_P on 15/04/2026
 */
class CheckFavoriteStatus (
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(movieId: Int): utils.Result<Boolean> = movieRepository.checkFavoriteStatus(movieId)
}