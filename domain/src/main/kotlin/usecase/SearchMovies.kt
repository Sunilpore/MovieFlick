package usecase

import androidx.paging.PagingData
import entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import repository.MovieRepository

class SearchMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(query: String, pageSize: Int): Flow<PagingData<MovieEntity>> = movieRepository.search(query, pageSize)
}