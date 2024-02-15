package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import barrera.alejandro.dondeloveo.data.enums.MediaContentType
import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview

fun SearchByTitleResponseDto.SearchResultDto.toMediaContentOverview(): MediaContentOverview {
    return when (MediaContentType.fromValue(tmdbType)) {
        MediaContentType.MOVIE -> this.toMovieOverview()
        MediaContentType.TV -> this.toSeriesOverview()
    }
}

fun SearchByTitleResponseDto.SearchResultDto.toMovieOverview(): MovieOverview {
    return MovieOverview(
        id = id,
        title = name ?: "Title not available",
        year = year ?: 0,
        imageUrl = imageUrl
    )
}

fun SearchByTitleResponseDto.SearchResultDto.toSeriesOverview(): SeriesOverview {
    return SeriesOverview(
        id = id,
        title = name ?: "Title not available",
        year = year ?: 0,
        imageUrl = imageUrl
    )
}