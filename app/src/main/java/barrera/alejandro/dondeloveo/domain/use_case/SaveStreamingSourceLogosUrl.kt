package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class SaveStreamingSourceLogosUrl(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke() {
        return mediaContentRepository.saveStreamingSourceLogosUrl()
    }
}