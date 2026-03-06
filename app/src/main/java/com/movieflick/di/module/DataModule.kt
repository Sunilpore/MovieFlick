package com.movieflick.di.module

import MovieRemoteDataSource
import api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.movie.MovieDataSource
import repository.movie.MovieRemoteMediator
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieMediator(
        movieLocalDataSource: MovieDataSource.Local,
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRemoteMediator {
        return MovieRemoteMediator(movieLocalDataSource, movieRemoteDataSource)
    }

}