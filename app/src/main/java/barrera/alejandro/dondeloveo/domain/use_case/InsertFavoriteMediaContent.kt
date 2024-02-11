package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class InsertFavoriteMediaContent(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke(mediaContentDetails: MediaContentDetails) {
        return mediaContentRepository.insertFavoriteMediaContent(mediaContentDetails)
    }
}