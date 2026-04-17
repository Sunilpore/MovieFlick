package usecase

import entities.MovieEntity
import repository.MovieRepository
import utils.Result

class GetMovieDetails (
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(movieId: Int): Result<MovieEntity> = movieRepository.getMovie(movieId)
}