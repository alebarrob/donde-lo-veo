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

class SearchByTitleTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val searchByTitle = SearchByTitle(mockMediaContentRepository)

    @Test
    fun `test SearchByTitle invokes correct repository method`() = runTest {
        val testQuery = "test"
        val mediaContentOverviewListResult = Result.success(listOf<MediaContentOverview>())

        `when`(mockMediaContentRepository.searchByTitle(testQuery)).thenReturn(
            mediaContentOverviewListResult
        )

        val actual = searchByTitle(testQuery)

        verify(mockMediaContentRepository, times(1)).searchByTitle(testQuery)
        assertEquals(mediaContentOverviewListResult, actual)
        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}