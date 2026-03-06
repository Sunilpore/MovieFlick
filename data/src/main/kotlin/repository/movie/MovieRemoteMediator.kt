package repository.movie


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import entities.MovieDbData
import entities.MovieRemoteKeyDbData
import utils.Result

private const val MOVIE_STARTING_PAGE_INDEX = 1


/**
 * Created By Sunil_P on 20/02/2026
 */
@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator (
    private val local: MovieDataSource.Local,
    private val remote: MovieDataSource.Remote
): RemoteMediator<Int, MovieDbData>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieDbData>): MediatorResult {

       val page = when(loadType){
            LoadType.REFRESH -> MOVIE_STARTING_PAGE_INDEX
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        LogUtils.d( "MovieRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}")

        if(state.isEmpty() && page ==2) return MediatorResult.Success(endOfPaginationReached = false)

        when(val result = remote.getMovies(page, state.config.pageSize)){
            is Result.Success -> {
                LogUtils.d("MovieRemoteMediator: get movies from remote")

                if(loadType == LoadType.REFRESH){
                    local.clearMovies()
                    local.clearRemoteKeys()
                }

                val movies = result.data

                val endOfPaginationReached = movies.isEmpty()

                val prevPage = if(page == MOVIE_STARTING_PAGE_INDEX) null else page -1
                val nextPage = if(endOfPaginationReached) null else page + 1

                val key = MovieRemoteKeyDbData(prevPage = prevPage, nextPage = nextPage)

                local.saveMovies(movies)
                local.saveRemoteKey(key)

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

            is Result.Error -> {
                return MediatorResult.Error(result.error)
            }
        }
    }

}