package barrera.alejandro.dondeloveo.data.di

import androidx.room.Room
import barrera.alejandro.dondeloveo.data.local.database.Database
import barrera.alejandro.dondeloveo.data.remote.api.WatchmodeApi
import barrera.alejandro.dondeloveo.data.repository.MediaContentRepositoryImpl
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(WatchmodeApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WatchmodeApi::class.java)
    }
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, "donde_lo_veo_database")
            .build()
    }
    single {
        val database: Database = get()
        database.streamingSourceLogoUrlDao()
    }
    single<MediaContentRepository> {
        MediaContentRepositoryImpl(get(), get())
    }
}