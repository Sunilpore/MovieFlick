package repository.movie

import androidx.paging.PagingSource
import entities.MovieData
import entities.MovieDbData
import entities.MovieEntity
import entities.MovieRemoteKeyDbData


interface MovieDataSource {

    interface Remote {
        suspend fun getMovies(page: Int, limit: Int): utils.Result<List<MovieData>>
        suspend fun getMovies(movieIds: List<Int>): utils.Result<List<MovieData>>
        suspend fun getMovie(movieId: Int): utils.Result<MovieData>
        suspend fun search(query:String, page:Int, limit: Int): utils.Result<List<MovieData>>
    }

    interface Local {
        fun movies(): PagingSource<Int, MovieDbData>
        suspend fun getMovies(): utils.Result<List<MovieEntity>>
        suspend fun getMovie(movieId: Int): utils.Result<MovieEntity>
        suspend fun saveMovies(movies: List<MovieData>)
        suspend fun getLastRemoteKey(): MovieRemoteKeyDbData?
        suspend fun saveRemoteKey(key: MovieRemoteKeyDbData)
        suspend fun clearMovies()
        suspend fun clearRemoteKeys()
    }

}