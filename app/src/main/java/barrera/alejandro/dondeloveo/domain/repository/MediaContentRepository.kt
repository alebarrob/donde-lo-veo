package barrera.alejandro.dondeloveo.domain.repository

import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview

interface MediaContentRepository {
    suspend fun saveStreamingSourceLogosUrl()

    suspend fun searchByTitle(query: String): Result<List<MediaContentOverview>>

    suspend fun getMediaContentDetails(mediaContentId: Int): Result<MediaContentDetails>

    suspend fun insertFavoriteMediaContent(mediaContentDetails: MediaContentDetails)

    suspend fun getAllFavoriteMediaContentOverview(): List<MediaContentOverview>

    suspend fun getFavoriteMediaContentDetails(mediaContentId: Int): MediaContentDetails

    suspend fun deleteFavoriteMediaContent(mediaContentId: Int)
}