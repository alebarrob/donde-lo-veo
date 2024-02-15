package barrera.alejandro.dondeloveo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cast")
data class CastMemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mediaContentId: Int,
    val name: String,
    val imageUrl: String?,
    val role: String
)