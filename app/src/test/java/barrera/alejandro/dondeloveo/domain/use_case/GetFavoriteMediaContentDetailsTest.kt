package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

class GetFavoriteMediaContentDetailsTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val getFavoriteMediaContentDetails =
        GetFavoriteMediaContentDetails(mockMediaContentRepository)

    @Test
    fun `test GetFavoriteMediaContentDetails invokes correct repository method`() = runTest {
        val testMediaContentId = 1
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

        `when`(mockMediaContentRepository.getFavoriteMediaContentDetails(anyInt())).thenReturn(
            movieDetails
        )

        val actual = getFavoriteMediaContentDetails(testMediaContentId)

        verify(
            mockMediaContentRepository, times(1)
        ).getFavoriteMediaContentDetails(testMediaContentId)
        assertEquals(movieDetails, actual)
        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}