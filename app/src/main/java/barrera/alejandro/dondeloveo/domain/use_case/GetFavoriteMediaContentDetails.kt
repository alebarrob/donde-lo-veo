package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContentDetails
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class GetFavoriteMediaContentDetails(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke(mediaContentId: Int): MediaContentDetails {
        return mediaContentRepository.getFavoriteMediaContentDetails(mediaContentId)
    }
}