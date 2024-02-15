package barrera.alejandro.dondeloveo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceEntity

@Dao
interface StreamingSourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreamingSources(streamingSources: List<StreamingSourceEntity>)

    @Query("DELETE FROM streamingSources WHERE mediaContentId = :id")
    suspend fun deleteStreamingSources(id: Int)
}