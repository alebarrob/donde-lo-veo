package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class GetMediaContentDetailsTest {
    private val mockMediaContentRepository = Mockito.mock(MediaContentRepository::class.java)
    private val getMediaContentDetails =
        GetMediaContentDetails(mockMediaContentRepository)

    @Test
    fun `test GetMediaContentDetails invokes correct repository method`() = runTest {
        val testMediaContentId = 1
        val movieDetailsResult = Result.success(
            MovieDetails(
                id = 1,
                title = "Title",
                year = 2023,
                imageUrl = "imageUrl",
                description = "description",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )

        Mockito.`when`(mockMediaContentRepository.getMediaContentDetails(Mockito.anyInt()))
            .thenReturn(
                movieDetailsResult
            )

        val actual = getMediaContentDetails(testMediaContentId)

        Mockito.verify(
            mockMediaContentRepository, Mockito.times(1)
        ).getMediaContentDetails(testMediaContentId)
        Assert.assertEquals(movieDetailsResult, actual)
        Mockito.verifyNoMoreInteractions(mockMediaContentRepository)
    }
}