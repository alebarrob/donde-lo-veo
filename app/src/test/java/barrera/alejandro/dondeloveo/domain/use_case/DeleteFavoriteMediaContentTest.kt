package barrera.alejandro.dondeloveo.domain.use_case

import barrera.alejandro.dondeloveo.domain.repository.MediaContentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class DeleteFavoriteMediaContentTest {
    private val mockMediaContentRepository = mock(MediaContentRepository::class.java)
    private val deleteFavoriteMediaContent = DeleteFavoriteMediaContent(mockMediaContentRepository)

    @Test
    fun `test deleteFavoriteMediaContent invokes correct repository method`() = runTest {
        val testMediaContentId = 1

        deleteFavoriteMediaContent(testMediaContentId)

        verify(
            mockMediaContentRepository, times(1)
        ).deleteFavoriteMediaContent(testMediaContentId)

        verifyNoMoreInteractions(mockMediaContentRepository)
    }
}