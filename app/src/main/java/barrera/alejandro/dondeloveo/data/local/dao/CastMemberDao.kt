package barrera.alejandro.dondeloveo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.dondeloveo.data.local.entity.CastMemberEntity

@Dao
interface CastMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCastMembers(castMembers: List<CastMemberEntity>)

    @Query("DELETE FROM `cast` WHERE mediaContentId = :id")
    suspend fun deleteCastMember(id: Int)
}