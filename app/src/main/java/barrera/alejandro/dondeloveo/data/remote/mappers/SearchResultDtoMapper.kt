package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import barrera.alejandro.dondeloveo.data.remote.enums.TmdbTypes
import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview

fun SearchByTitleResponseDto.SearchResultDto.toMediaContentOverview(): MediaContentOverview {
    return when (TmdbTypes.fromValue(tmdbType)) {
        TmdbTypes.MOVIE -> this.toMovieOverview()
        TmdbTypes.TV -> this.toSeriesOverview()
    }
}

fun SearchByTitleResponseDto.SearchResultDto.toMovieOverview(): MediaContentOverview {
    return MovieOverview(
        id = id,
        title = name ?: "Title not available",
        year = year ?: 0,
        imageUrl = imageUrl
    )
}

fun SearchByTitleResponseDto.SearchResultDto.toSeriesOverview(): MediaContentOverview {
    return SeriesOverview(
        id = id,
        title = name ?: "Title not available",
        year = year ?: 0,
        imageUrl = imageUrl
    )
}