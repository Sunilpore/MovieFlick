package com.movieflick.di.module

import MovieRemoteDataSource
import api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.MovieRepository
import repository.movie.MovieDataSource
import repository.movie.MovieRemoteMediator
import repository.movie.MovieRepositoryImpl
import repository.movie.favorite.FavoriteMoviesDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    /*@Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local,
        movieRemoteMediator: MovieRemoteMediator,
        favoriteLocal: FavoriteMoviesDataSource.Local,
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal, movieRemoteMediator, favoriteLocal);
    }*/

    /*@Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi)
    }*/

    /*@Provides
    @Singleton
    fun provideMovieMediator(
        movieLocalDataSource: MovieDataSource.Local,
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRemoteMediator {
        return MovieRemoteMediator(movieLocalDataSource, movieRemoteDataSource)
    }*/

}