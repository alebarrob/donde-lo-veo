package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.enums.MediaContentType
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentWithRelations
import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails

fun FavoriteMediaContentWithRelations.toMediaContentDetails(): MediaContentDetails {
    return when (MediaContentType.fromValue(favoriteMediaContent.type)) {
        MediaContentType.MOVIE -> this.toMovieDetails()
        MediaContentType.TV -> this.toSeriesDetails()
    }
}

fun FavoriteMediaContentWithRelations.toMovieDetails(): MovieDetails {
    with(favoriteMediaContent) {
        return MovieDetails(
            id = id,
            title = title,
            year = year,
            imageUrl = imageUrl,
            description = description,
            streamingSources = streamingSources.map { streamingSourceEntity ->
                streamingSourceEntity.toStreamingSource()
            },
            crew = crew.map { crewEntity ->
                crewEntity.toCrewMember()
            },
            cast = cast.map { castEntity ->
                castEntity.toCastMember()
            }
        )
    }
}

fun FavoriteMediaContentWithRelations.toSeriesDetails(): SeriesDetails {
    with(favoriteMediaContent) {
        return SeriesDetails(
            id = id,
            title = title,
            year = year,
            endYear = endYear,
            imageUrl = imageUrl,
            description = description,
            streamingSources = streamingSources.map { streamingSourceEntity ->
                streamingSourceEntity.toStreamingSource()
            },
            crew = crew.map { crewEntity ->
                crewEntity.toCrewMember()
            },
            cast = cast.map { castEntity ->
                castEntity.toCastMember()
            }
        )
    }
}