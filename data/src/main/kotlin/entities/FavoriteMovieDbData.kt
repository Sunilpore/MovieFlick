package entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created By Sunil_P on 11/03/2026
 */

@Entity(tableName = "favorite_movies")
data class FavoriteMovieDbData (
    @PrimaryKey val movieId: Int
)