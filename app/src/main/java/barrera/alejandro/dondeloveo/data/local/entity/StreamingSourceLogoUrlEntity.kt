package barrera.alejandro.dondeloveo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streamingSourceLogosUrl")
data class StreamingSourceLogoUrlEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String?
)