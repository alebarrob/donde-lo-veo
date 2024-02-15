package barrera.alejandro.dondeloveo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.dondeloveo.data.local.dao.CastMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.CrewMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.FavoriteMediaContentDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.entity.CastMemberEntity
import barrera.alejandro.dondeloveo.data.local.entity.CrewMemberEntity
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceEntity
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity

@Database(
    entities = [
        StreamingSourceLogoUrlEntity::class,
        CastMemberEntity::class,
        CrewMemberEntity::class,
        StreamingSourceEntity::class,
        FavoriteMediaContentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun streamingSourceLogoUrlDao(): StreamingSourceLogoUrlDao
    abstract fun castMemberDao(): CastMemberDao
    abstract fun crewMemberDao(): CrewMemberDao
    abstract fun favoriteMediaContentDao(): FavoriteMediaContentDao
    abstract fun streamingSourceDao(): StreamingSourceDao
}