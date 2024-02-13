package barrera.alejandro.dondeloveo.data.repository

import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity
import barrera.alejandro.dondeloveo.data.remote.api.WatchmodeApi
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import barrera.alejandro.dondeloveo.data.remote.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.data.remote.mappers.toMediaContentOverview
import barrera.alejandro.dondeloveo.data.remote.mappers.toStreamingSourceLogoUrlEntity
import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class MediaContentRepositoryImpl(
    private val api: WatchmodeApi,
    private val streamingSourceLogoUrlDao: StreamingSourceLogoUrlDao,
) : MediaContentRepository {
    override suspend fun saveStreamingSourceLogosUrl() {
        getStreamingSourceLogosUrlEntities().getOrNull()?.let { streamingSourceLogosUrlEntities ->
            streamingSourceLogoUrlDao.upsertStreamingSourceLogosUrl(streamingSourceLogosUrlEntities)
        }
    }

    private suspend fun getStreamingSourceLogosUrlEntities():
            Result<List<StreamingSourceLogoUrlEntity>> {
        return try {
            Result.success(
                api.getAllStreamingSourceLogos().map { streamingSourceLogoUrlDto ->
                    streamingSourceLogoUrlDto.toStreamingSourceLogoUrlEntity()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun searchByTitle(query: String): Result<List<MediaContentOverview>> {
        return try {
            Result.success(
                api.searchByTitle(query = query).results.map { searchByTitleResponseDto ->
                    searchByTitleResponseDto.toMediaContentOverview()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getMediaContentDetails(mediaContentId: Int): Result<MediaContentDetails> {
        val teamMemberDtos = getTeamMemberDtos(mediaContentId).getOrNull()
        val streamingSourceDtosWithImageUrls =
            getStreamingSourceDtosWithImageUrls(mediaContentId).getOrNull()

        return try {
            Result.success(
                api.getDetails(mediaContentId = mediaContentId).toMediaContentDetails(
                    streamingSourceDtosWithImageUrls = streamingSourceDtosWithImageUrls,
                    teamMemberDtos = teamMemberDtos,
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private suspend fun getTeamMemberDtos(mediaContentId: Int): Result<List<TeamMemberDto>> {
        return try {
            Result.success(
                api.getTeamMembers(mediaContentId = mediaContentId)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private suspend fun getStreamingSourceDtosWithImageUrls(
        mediaContentId: Int
    ): Result<Map<StreamingSourceDto, String>> {
        return try {
            Result.success(
                api.getStreamingSources(mediaContentId).associateWith { streamingSourceDto ->
                    getStreamingSourceLogoUrlById(streamingSourceDto.sourceId)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private suspend fun getStreamingSourceLogoUrlById(id: Int): String {
        return streamingSourceLogoUrlDao.getStreamingSourceLogoUrlById(id)
    }

    override suspend fun insertFavoriteMediaContent(mediaContentDetails: MediaContentDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavoriteMediaContentOverview(): List<MediaContentOverview> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteMediaContentDetails(mediaContentId: Int): MediaContentDetails {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteMediaContent(mediaContentId: Int) {
        TODO("Not yet implemented")
    }
}