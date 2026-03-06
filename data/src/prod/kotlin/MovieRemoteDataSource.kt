import api.MovieApi
import entities.MovieData
import repository.movie.MovieDataSource
import utils.Result
import utils.safeApiCall


/**
 * Created By Sunil_P on 25/02/2026
 */
class MovieRemoteDataSource (
    private val movieApi: MovieApi
): MovieDataSource.Remote {

    override suspend fun getMovies(page: Int, limit: Int): Result<List<MovieData>> = safeApiCall {
        movieApi.getMovies(page, limit)
    }

    override suspend fun getMovies(movieIds: List<Int>): Result<List<MovieData>> = safeApiCall {
        movieApi.getMovies(movieIds)
    }

    override suspend fun getMovie(movieId: Int): Result<MovieData> = safeApiCall {
        movieApi.getMovie(movieId)
    }

    override suspend fun search(query: String, page: Int, limit: Int): Result<List<MovieData>> = safeApiCall {
        movieApi.search(query, page, limit)
    }

}