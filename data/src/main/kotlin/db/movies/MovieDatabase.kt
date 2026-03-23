package db.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import db.favoritemovies.FavoriteMovieDao
import entities.FavoriteMovieDbData
import entities.MovieDbData
import entities.MovieRemoteKeyDbData

/**
 * Created By Sunil_P on 17/03/2026
 */
@Database(
    entities = [MovieDbData::class, FavoriteMovieDbData::class, MovieRemoteKeyDbData::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeyDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}