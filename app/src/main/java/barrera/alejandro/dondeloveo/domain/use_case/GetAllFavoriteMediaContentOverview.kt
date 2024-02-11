package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class GetAllFavoriteMediaContentOverview(
    private val mediaContentRepository: MediaContentRepository
) {
    suspend operator fun invoke(): List<MediaContentOverview> {
        return mediaContentRepository.getAllFavoriteMediaContentOverview()
    }
}