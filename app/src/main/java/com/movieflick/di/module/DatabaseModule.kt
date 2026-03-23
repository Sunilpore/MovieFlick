package com.movieflick.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import db.favoritemovies.FavoriteMovieDao
import db.movies.MovieDao
import db.movies.MovieDatabase
import db.movies.MovieRemoteKeyDao
import utils.DiskExecutor
import javax.inject.Singleton

/**
 * Created By Sunil_P on 17/03/2026
*/
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
        diskExecutor: DiskExecutor): MovieDatabase {

        return Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            .setQueryExecutor (diskExecutor)
            .setTransactionExecutor(diskExecutor)
            .build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Provides
    fun provideMovieRemoteKeyDao(movieDatabase: MovieDatabase): MovieRemoteKeyDao {
        return movieDatabase.movieRemoteKeysDao()
    }

    @Provides
    fun provideFavoriteMovieDao(movieDatabase: MovieDatabase): FavoriteMovieDao {
        return movieDatabase.favoriteMovieDao()
    }

}