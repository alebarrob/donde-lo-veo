package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository

class SearchByTitle(private val mediaContentRepository: MediaContentRepository) {
    suspend operator fun invoke(query: String): Result<List<MediaContentOverview>> {
        return mediaContentRepository.searchByTitle(query)
    }
}