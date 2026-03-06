package entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sunil_P on 19/02/2026
 */
@Entity(tableName = "movies")
data class MovieDbData(
    @PrimaryKey val id: Int,
    val description: String,
    val image: String,
    val backgroundUrl: String,
    val title: String,
    val category: String
)


fun MovieDbData.toDomain() = MovieEntity(
    id = id,
    title = title,
    description = description,
    image = image,
    category = category,
    backgroundUrl = backgroundUrl
)