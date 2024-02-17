package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.model.MediaContentOverview
import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

class GetAllFavoriteMediaContentOverviewTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val getAllFavoriteMediaContentOverview =
        GetAllFavoriteMediaContentOverview(mockMediaContentRepository)

    @Test
    fun `test GetAllFavoriteMediaContentOverview invokes correct repository method`() = runTest {
        val testMediaContentOverviewList = listOf<MediaContentOverview>()

        `when`(mockMediaContentRepository.getAllFavoriteMediaContentOverview()).thenReturn(
            testMediaContentOverviewList
        )

        val actual = getAllFavoriteMediaContentOverview()

        verify(
            mockMediaContentRepository, times(1)
        ).getAllFavoriteMediaContentOverview()
        assertEquals(testMediaContentOverviewList, actual)
        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}