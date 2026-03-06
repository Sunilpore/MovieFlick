package entities

import com.google.gson.annotations.SerializedName


/**
 * Created by Sunil_P on 20/02/2026
 */
data class MovieData (
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("backgroundUrl") val backgroundUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
)

fun MovieData.toDomain() = MovieEntity(
    id = id,
    image = image,
    backgroundUrl = backgroundUrl,
    description = description,
    title = title,
    category = category
)

fun MovieData.toDbData() = MovieDbData(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category,
    backgroundUrl = backgroundUrl
)