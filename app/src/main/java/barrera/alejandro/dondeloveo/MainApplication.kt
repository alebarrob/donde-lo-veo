package barrera.alejandro.dondeloveo

import android.app.Application
import barrera.alejandro.dondeloveo.data.di.dataModule
import barrera.alejandro.dondeloveo.domain.di.domainModule
import barrera.alejandro.dondeloveo.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MainApplication)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}