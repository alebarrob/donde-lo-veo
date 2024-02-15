package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.MediaContentDto
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import barrera.alejandro.dondeloveo.data.enums.MediaContentType
import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails

fun MediaContentDto.toMediaContentDetails(
    streamingSourceDtosWithImageUrls: Map<StreamingSourceDto, String>?,
    teamMemberDtos: List<TeamMemberDto>?
): MediaContentDetails {
    return when (MediaContentType.fromValue(tmdbType)) {
        MediaContentType.MOVIE -> this.toMovieDetails(streamingSourceDtosWithImageUrls, teamMemberDtos)
        MediaContentType.TV -> this.toSeriesDetails(streamingSourceDtosWithImageUrls, teamMemberDtos)
    }
}

private fun MediaContentDto.toMovieDetails(
    streamingSourceDtosWithImageUrls: Map<StreamingSourceDto, String>?,
    teamMemberDtos: List<TeamMemberDto>?
): MovieDetails {
    val teamMembers = teamMemberDtos?.map { it.toTeamMember() }

    return MovieDetails(
        id = id,
        title = title ?: "Title not available",
        year = year ?: 0,
        imageUrl = poster,
        description = plotOverview ?: "Description not available",
        streamingSources = streamingSourceDtosWithImageUrls?.map { (streamingSourceDto, imageUrl) ->
            streamingSourceDto.toStreamingSource(imageUrl)
        } ?: emptyList(),
        crew = teamMembers?.filterIsInstance<CrewMember>() ?: emptyList(),
        cast = teamMembers?.filterIsInstance<CastMember>() ?: emptyList()
    )
}

private fun MediaContentDto.toSeriesDetails(
    streamingSourceDtosWithImageUrls: Map<StreamingSourceDto, String>?,
    teamMemberDtos: List<TeamMemberDto>?
): SeriesDetails {
    val teamMembers = teamMemberDtos?.map { it.toTeamMember() }

    return SeriesDetails(
        id = id,
        title = title ?: "Title not available",
        year = year ?: 0,
        endYear = endYear,
        imageUrl = poster,
        description = plotOverview ?: "Description not available",
        streamingSources = streamingSourceDtosWithImageUrls?.map { (streamingSourceDto, imageUrl) ->
            streamingSourceDto.toStreamingSource(imageUrl)
        } ?: emptyList(),
        crew = teamMembers?.filterIsInstance<CrewMember>() ?: emptyList(),
        cast = teamMembers?.filterIsInstance<CastMember>() ?: emptyList()
    )
}