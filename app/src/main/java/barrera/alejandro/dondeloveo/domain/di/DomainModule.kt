package barrera.alejandro.dondeloveo.domain.di

import barrera.alejandro.dondeloveo.domain.use_case.DeleteFavoriteMediaContent
import barrera.alejandro.dondeloveo.domain.use_case.GetAllFavoriteMediaContentOverview
import barrera.alejandro.dondeloveo.domain.use_case.GetFavoriteMediaContentDetails
import barrera.alejandro.dondeloveo.domain.use_case.InsertFavoriteMediaContent
import barrera.alejandro.dondeloveo.domain.use_case.SaveStreamingSourceLogosUrl
import barrera.alejandro.dondeloveo.domain.use_case.SearchByTitle
import org.koin.dsl.module

val domainModule = module {
    factory {
        DeleteFavoriteMediaContent(get())
    }
    factory {
        GetAllFavoriteMediaContentOverview(get())
    }
    factory {
        GetFavoriteMediaContentDetails(get())
    }
    factory {
        InsertFavoriteMediaContent(get())
    }
    factory {
        SaveStreamingSourceLogosUrl(get())
    }
    factory {
        SearchByTitle(get())
    }
}