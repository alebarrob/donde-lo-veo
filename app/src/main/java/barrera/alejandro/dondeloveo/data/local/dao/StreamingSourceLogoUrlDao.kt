package barrera.alejandro.dondeloveo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity

@Dao
interface StreamingSourceLogoUrlDao {
    @Upsert
    suspend fun upsertStreamingSourceLogosUrl(
        streamingSourceLogosUrlEntities: List<StreamingSourceLogoUrlEntity>
    )

    @Query("SELECT imageUrl FROM streamingSourceLogosUrl WHERE id = :id")
    suspend fun getStreamingSourceLogoUrlById(id: Int): String
}