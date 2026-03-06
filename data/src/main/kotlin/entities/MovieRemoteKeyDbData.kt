package entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created By Sunil_P on 20/02/2026
 */
@Entity(tableName = "movies_remote_keys")
data class MovieRemoteKeyDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)