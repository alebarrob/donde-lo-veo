package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class SaveStreamingSourceLogosUrlTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val saveStreamingSourceLogosUrl =
        SaveStreamingSourceLogosUrl(mockMediaContentRepository)

    @Test
    fun `test SaveStreamingSourceLogosUrl invokes correct repository method`() = runTest {
        saveStreamingSourceLogosUrl()

        verify(mockMediaContentRepository, times(1)).saveStreamingSourceLogosUrl()
        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}