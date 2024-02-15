package barrera.alejandro.dondeloveo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.dondeloveo.data.local.entity.CrewMemberEntity

@Dao
interface CrewMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrewMembers(crewMembers: List<CrewMemberEntity>)

    @Query("DELETE FROM `crew` WHERE mediaContentId = :id")
    suspend fun deleteCrewMember(id: Int)
}