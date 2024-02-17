package barrera.alejandro.dondeloveo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.use_case.GetAllFavoriteMediaContentOverview
import barrera.alejandro.dondeloveo.domain.use_case.SearchByTitle
import barrera.alejandro.dondeloveo.presentation.mappers.toUiMediaContentOverview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ExploreViewModelTest {
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val searchByTitle: SearchByTitle = mock(SearchByTitle::class.java)
    private val getAllFavoriteMediaContentOverview: GetAllFavoriteMediaContentOverview =
        mock(GetAllFavoriteMediaContentOverview::class.java)

    private lateinit var viewModel: ExploreViewModel

    @Before
    fun setUp() {
        viewModel = ExploreViewModel(searchByTitle, getAllFavoriteMediaContentOverview)
    }

    @Test
    fun `When updating favorite screen, observed favorite screen LiveData reflects the change`() =
        runTest {
            val isFavoriteScreenTestValue = true

            viewModel.updateIsFavoriteScreen(isFavoriteScreenTestValue)

            assertEquals(isFavoriteScreenTestValue, viewModel.isFavoriteScreen.value)
        }

    @Test
    fun `given query when onSearchByTitle then show media content items`() = runTest {
        val mediaContents = listOf(
            MovieOverview(
                id = 1,
                title = "Title",
                year = 2020,
                imageUrl = "imageUrl"
            )
        )
        val expected = mediaContents.map { it.toUiMediaContentOverview() }

        `when`(searchByTitle.invoke(anyString())).thenReturn(Result.success(mediaContents))
        viewModel.onSearchByTitle(query = "query")
        advanceUntilIdle()

        assertEquals(expected, viewModel.mediaContentItems.value)
        assertEquals(false, viewModel.showCircularProgressBarEvent.value?.getContentIfNotHandled())
    }

    @Test
    fun `given query when onSearchByTitle fails then circular progress bar disappears`() = runTest {
        `when`(searchByTitle.invoke(anyString())).thenReturn(Result.failure(Exception()))
        viewModel.onSearchByTitle(query = "query")
        advanceUntilIdle()

        assertEquals(false, viewModel.showCircularProgressBarEvent.value?.getContentIfNotHandled())
    }

    @Test
    fun `When More Info button clicked with position, navigateToDetailsFragmentEvent populated with that position's content`() =
        runTest {
            val mediaContents = listOf(
                MovieOverview(
                    id = 1,
                    title = "Title",
                    year = 2020,
                    imageUrl = "imageUrl"
                ),
                MovieOverview(
                    id = 2,
                    title = "Title",
                    year = 2020,
                    imageUrl = "imageUrl"
                )
            )
            val expectedPosition = 0
            val expectedItem = mediaContents[expectedPosition].toUiMediaContentOverview()

            `when`(searchByTitle.invoke(anyString())).thenReturn(Result.success(mediaContents))
            viewModel.onSearchByTitle("query")
            advanceUntilIdle()
            viewModel.onClickMoreInfoButton(expectedPosition)
            advanceUntilIdle()

            assertEquals(
                expectedItem,
                viewModel.navigateToDetailsFragmentEvent.value?.getContentIfNotHandled()
            )
        }

    @Test
    fun `When loading favorite media content, mediaContentItems is correctly populated`() =
        runTest {
            val favoriteMediaContent = listOf(
                MovieOverview(
                    id = 1,
                    title = "Title",
                    year = 2020,
                    imageUrl = "imageUrl"
                ),
            )
            val expected = favoriteMediaContent.map { it.toUiMediaContentOverview() }

            `when`(getAllFavoriteMediaContentOverview.invoke()).thenReturn(favoriteMediaContent)
            viewModel.loadFavoriteMediaContent()
            advanceUntilIdle()

            assertEquals(expected, viewModel.mediaContentItems.value)
        }
}