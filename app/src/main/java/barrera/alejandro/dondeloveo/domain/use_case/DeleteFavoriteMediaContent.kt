package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class DeleteFavoriteMediaContent(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke(mediaContentId: Int) {
        return mediaContentRepository.deleteFavoriteMediaContent(mediaContentId)
    }
}