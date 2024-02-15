package barrera.alejandro.dondeloveo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streamingSources")
data class StreamingSourceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mediaContentId: Int,
    val name: String,
    val imageUrl: String?,
    val webUrl: String?
)
