package barrera.alejandro.dondeloveo.presentation

import barrera.alejandro.dondeloveo.domain.use_case.SaveStreamingSourceLogosUrl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private val saveStreamingSourceLogosUrl = mock(SaveStreamingSourceLogosUrl::class.java)
    private val mainViewModel = MainViewModel(saveStreamingSourceLogosUrl)

    @Test
    fun `test saveStreamingSourceLogosUrl is called when ViewModel function is invoked`() =
        runTest {
            mainViewModel.saveStreamingSourceLogosUrl()
            advanceUntilIdle()

            verify(saveStreamingSourceLogosUrl, times(1)).invoke()
        }
}