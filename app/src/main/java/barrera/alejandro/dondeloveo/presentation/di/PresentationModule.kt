package barrera.alejandro.dondeloveo.presentation.di

import barrera.alejandro.dondeloveo.presentation.ExploreViewModel
import barrera.alejandro.dondeloveo.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        ExploreViewModel(get(), get())
    }
}