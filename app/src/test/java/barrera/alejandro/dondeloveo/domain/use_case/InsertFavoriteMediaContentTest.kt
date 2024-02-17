package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class InsertFavoriteMediaContentTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val insertFavoriteMediaContent = InsertFavoriteMediaContent(mockMediaContentRepository)

    @Test
    fun `test InsertFavoriteMediaContent invokes correct repository method`() = runTest {
        val movieDetails = MovieDetails(
            id = 1,
            title = "Title",
            year = 2023,
            imageUrl = "imageUrl",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )

        insertFavoriteMediaContent(movieDetails)

        verify(
            mockMediaContentRepository, times(1)
        ).insertFavoriteMediaContent(movieDetails)
        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}