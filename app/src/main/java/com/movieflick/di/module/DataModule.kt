package com.movieflick.di.module

import MovieRemoteDataSource
import android.content.Context
import api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import db.favoritemovies.FavoriteMovieDao
import db.movies.MovieDao
import db.movies.MovieRemoteKeyDao
import repository.MovieRepository
import repository.movie.MovieDataSource
import repository.movie.MovieLocalDataSource
import repository.movie.MovieRemoteMediator
import repository.movie.MovieRepositoryImpl
import repository.movie.favorite.FavoriteMoviesDataSource
import repository.movie.favorite.FavoriteMoviesLocalDataSource
import usecase.GetFavoriteMovies
import utils.NetworkMonitor
import utils.NetworkMonitorImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local,
        movieRemoteMediator: MovieRemoteMediator,
        favoriteLocal: FavoriteMoviesDataSource.Local,
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal, movieRemoteMediator, favoriteLocal)
    }

    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        movieDao: MovieDao,
        movieRemoteKeyDao: MovieRemoteKeyDao,
    ): MovieDataSource.Local {
        return MovieLocalDataSource(movieDao, movieRemoteKeyDao)
    }

    //--------------------------------------------------------------------------------------------//

    @Provides
    @Singleton
    fun provideMovieMediator(
        movieLocalDataSource: MovieDataSource.Local,
        movieRemoteDataSource: MovieDataSource.Remote
    ): MovieRemoteMediator {
        return MovieRemoteMediator(movieLocalDataSource, movieRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieLocalDataSource(
        favoriteMovieDao: FavoriteMovieDao
    ): FavoriteMoviesDataSource.Local {
        return FavoriteMoviesLocalDataSource(favoriteMovieDao)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(movieRepository: MovieRepository): GetFavoriteMovies {
        return GetFavoriteMovies(movieRepository)
    }

    //--------------------------------------------------------------------------------------------//

    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = NetworkMonitorImpl(context)

    //--------------------------------------------------------------------------------------------//

}