package barrera.alejandro.dondeloveo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import barrera.alejandro.dondeloveo.data.enums.MediaContentType

@Entity(tableName = "favoriteMediaContent")
data class FavoriteMediaContentEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val year: Int,
    val endYear: Int?,
    val imageUrl: String?,
    val description: String,
    val type: String
)
