package barrera.alejandro.dondeloveo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentWithRelations

@Dao
interface FavoriteMediaContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMediaContent(favoriteMediaContent: FavoriteMediaContentEntity)

    @Transaction
    @Query("SELECT * FROM favoriteMediaContent")
    suspend fun getAllFavoriteMediaContent(): List<FavoriteMediaContentWithRelations>

    @Transaction
    @Query("SELECT * FROM favoriteMediaContent WHERE id = :id")
    suspend fun getFavoriteMediaContent(id: Int): FavoriteMediaContentWithRelations

    @Query("DELETE FROM favoriteMediaContent WHERE id = :id")
    suspend fun deleteFavoriteMediaContent(id: Int)
}