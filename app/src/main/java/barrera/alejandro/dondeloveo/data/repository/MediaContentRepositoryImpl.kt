package barrera.alejandro.dondeloveo.data.repository

import barrera.alejandro.dondeloveo.data.local.dao.CastMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.CrewMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.FavoriteMediaContentDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toCastMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toCrewMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toFavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentOverview
import barrera.alejandro.dondeloveo.data.local.mappers.toStreamingSourceEntity
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
    private val streamingSourceDao: StreamingSourceDao,
    private val crewMemberDao: CrewMemberDao,
    private val castMemberDao: CastMemberDao,
    private val favoriteMediaContentDao: FavoriteMediaContentDao
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
                api.getStreamingSources(mediaContentId).reversed().distinctBy {
                    it.sourceId
                }.associateWith { streamingSourceDto ->
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
        with(mediaContentDetails) {
            favoriteMediaContentDao.insertFavoriteMediaContent(
                this.toFavoriteMediaContentEntity()
            )
            crewMemberDao.insertCrewMembers(
                crew.map { crewMember ->
                    crewMember.toCrewMemberEntity(id)
                }
            )
            castMemberDao.insertCastMembers(
                cast.map { castMember ->
                    castMember.toCastMemberEntity(id)
                }
            )
            streamingSourceDao.insertStreamingSources(
                streamingSources.map { streamingSource ->
                    streamingSource.toStreamingSourceEntity(id)
                }
            )
        }
    }

    override suspend fun getAllFavoriteMediaContentOverview(): List<MediaContentOverview> {
        return favoriteMediaContentDao
            .getAllFavoriteMediaContent().map { favoriteMediaContentWithRelations ->
                favoriteMediaContentWithRelations.favoriteMediaContent.toMediaContentOverview()
            }
    }

    override suspend fun getFavoriteMediaContentDetails(mediaContentId: Int): MediaContentDetails {
        return favoriteMediaContentDao
            .getFavoriteMediaContent(mediaContentId).toMediaContentDetails()
    }

    override suspend fun deleteFavoriteMediaContent(mediaContentId: Int) {
        favoriteMediaContentDao.deleteFavoriteMediaContent(mediaContentId)
        crewMemberDao.deleteCrewMember(mediaContentId)
        castMemberDao.deleteCastMember(mediaContentId)
        streamingSourceDao.deleteStreamingSources(mediaContentId)
    }
}