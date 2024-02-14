package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContent
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class GetMediaContentDetails(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke(mediaContentId: Int): Result<MediaContent> {
        return mediaContentRepository.getMediaContentDetails(mediaContentId)
    }
}