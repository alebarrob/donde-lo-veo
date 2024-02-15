package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import barrera.alejandro.dondeloveo.data.enums.MediaContentType
import barrera.alejandro.dondeloveo.data.remote.mappers.toMovieOverview
import barrera.alejandro.dondeloveo.data.remote.mappers.toSeriesOverview
import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview

fun FavoriteMediaContentEntity.toMediaContentOverview(): MediaContentOverview {
    return when (MediaContentType.fromValue(type)) {
        MediaContentType.MOVIE -> this.toMovieOverview()
        MediaContentType.TV -> this.toSeriesOverview()
    }
}

fun FavoriteMediaContentEntity.toMovieOverview(): MovieOverview {
    return MovieOverview(
        id = id,
        title = title,
        year = year,
        imageUrl = imageUrl
    )
}

fun FavoriteMediaContentEntity.toSeriesOverview(): SeriesOverview {
    return SeriesOverview(
        id = id,
        title = title,
        year = year,
        imageUrl = imageUrl
    )
}

fun MediaContentDetails.toFavoriteMediaContentEntity(): FavoriteMediaContentEntity {
    return FavoriteMediaContentEntity(
        id = id,
        title = title,
        year = year,
        endYear = if (this is SeriesDetails) endYear else null,
        imageUrl = imageUrl,
        description = description,
        type = when(this) {
            is SeriesDetails -> "tv"
            else -> "movie"
        }
    )
}