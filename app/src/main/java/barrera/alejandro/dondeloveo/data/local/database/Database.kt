package barrera.alejandro.dondeloveo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity

@Database(
    entities = [
        StreamingSourceLogoUrlEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun streamingSourceLogoUrlDao(): StreamingSourceLogoUrlDao
}