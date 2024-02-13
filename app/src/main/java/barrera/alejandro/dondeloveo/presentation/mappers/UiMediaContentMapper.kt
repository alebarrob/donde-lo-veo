package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.MediaContent
import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview
import barrera.alejandro.dondeloveo.presentation.model.UiCastMember
import barrera.alejandro.dondeloveo.presentation.model.UiCrewMember
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContent
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMovieDetails
import barrera.alejandro.dondeloveo.presentation.model.UiMovieOverview
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesDetails
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesOverview

fun MediaContent.toUiMediaContentOverview(): UiMediaContentOverview {
    return when (this) {
        is MovieOverview -> this.toUiMovieOverview()
        is SeriesOverview -> this.toUiSeriesOverview()
        else -> throw IllegalStateException("Invalid MediaContent type")
    }
}

private fun MovieOverview.toUiMovieOverview(): UiMediaContentOverview {
    return UiMovieOverview(
        id = id,
        title = title,
        year = year.toString(),
        imageUrl = imageUrl
    )
}

private fun SeriesOverview.toUiSeriesOverview(): UiMediaContentOverview {
    return UiSeriesOverview(
        id = id,
        title = title,
        year = year.toString(),
        imageUrl = imageUrl
    )
}

fun UiMediaContent.toMediaContentDetails(): MediaContentDetails {
    return when (this) {
        is UiMovieDetails -> this.toMovieDetails()
        is UiSeriesDetails -> this.toSeriesDetails()
        else -> throw IllegalStateException("Invalid MediaContent type")
    }
}

private fun UiMovieDetails.toMovieDetails(): MediaContentDetails {
    return MovieDetails(
        id = id,
        title = title,
        year = year.toInt(),
        imageUrl = imageUrl,
        description = description,
        streamingSources = streamingSources.map { it.toStreamingSource() },
        crew = crew.map { (it.toTeamMember() as CrewMember) },
        cast = cast.map { (it.toTeamMember() as CastMember) }
    )
}

private fun UiSeriesDetails.toSeriesDetails(): MediaContentDetails {
    return SeriesDetails(
        id = id,
        title = title,
        year = year.toInt(),
        endYear = if (endYear != "Presente") endYear.toInt() else null,
        imageUrl = imageUrl,
        description = description,
        streamingSources = streamingSources.map { it.toStreamingSource() },
        crew = crew.map { (it.toTeamMember() as CrewMember) },
        cast = cast.map { (it.toTeamMember() as CastMember) }
    )
}

fun MediaContent.toUiMediaContentDetails(): UiMediaContentDetails {
    return when (this) {
        is MovieDetails -> this.toUiMovieDetails()
        is SeriesDetails -> this.toUiSeriesDetails()
        else -> throw IllegalStateException("Invalid MediaContent type")
    }
}

private fun MovieDetails.toUiMovieDetails(): UiMediaContentDetails {
    return UiMovieDetails(
        id = id,
        title = title,
        year = year.toString(),
        imageUrl = imageUrl,
        description = description,
        streamingSources = streamingSources.map { it.toUiStreamingSource() },
        crew = crew.map { it.toUiTeamMember() as UiCrewMember },
        cast = cast.map { it.toUiTeamMember() as UiCastMember }
    )
}

private fun SeriesDetails.toUiSeriesDetails(): UiMediaContentDetails {
    return UiSeriesDetails(
        id = id,
        title = title,
        year = year.toString(),
        endYear = endYear?.toString() ?: "Presente",
        imageUrl = imageUrl,
        description = description,
        streamingSources = streamingSources.map { it.toUiStreamingSource() },
        crew = crew.map { it.toUiTeamMember() as UiCrewMember },
        cast = cast.map { it.toUiTeamMember() as UiCastMember }
    )
}