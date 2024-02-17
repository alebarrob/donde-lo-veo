package barrera.alejandro.dondeloveo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.use_case.DeleteFavoriteMediaContent
import barrera.alejandro.dondeloveo.domain.use_case.GetFavoriteMediaContentDetails
import barrera.alejandro.dondeloveo.domain.use_case.GetMediaContentDetails
import barrera.alejandro.dondeloveo.domain.use_case.InsertFavoriteMediaContent
import barrera.alejandro.dondeloveo.presentation.mappers.toUiMediaContentDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMediaContentDetails: GetMediaContentDetails =
        Mockito.mock(GetMediaContentDetails::class.java)
    private val insertFavoriteMediaContent: InsertFavoriteMediaContent =
        Mockito.mock(InsertFavoriteMediaContent::class.java)
    private val getFavoriteMediaContentDetails: GetFavoriteMediaContentDetails =
        Mockito.mock(GetFavoriteMediaContentDetails::class.java)
    private val deleteFavoriteMediaContent: DeleteFavoriteMediaContent =
        Mockito.mock(DeleteFavoriteMediaContent::class.java)

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setUp() {
        viewModel = DetailsViewModel(
            getMediaContentDetails,
            insertFavoriteMediaContent,
            getFavoriteMediaContentDetails,
            deleteFavoriteMediaContent
        )
    }

    @Test
    fun `When updating media content id, value reflects the change`() = runTest {
        val mediaContentIdValue = 1

        viewModel.updateMediaContentId(mediaContentIdValue)

        Assert.assertEquals(mediaContentIdValue, viewModel.mediaContentId)
    }

    @Test
    fun `When updating favorite screen, observed favorite screen LiveData reflects the change`() =
        runTest {
            val isFavoriteScreenTestValue = true

            viewModel.updateIsFavoriteScreen(isFavoriteScreenTestValue)

            Assert.assertEquals(isFavoriteScreenTestValue, viewModel.isFavoriteScreen.value)
        }

    @Test
    fun `given media content id when loadMediaContentDetails then show media content item details`() =
        runTest {
            val mediaContentDetails = MovieDetails(
                id = 1,
                title = "Title",
                year = 2020,
                imageUrl = "imageUrl",
                description = "description",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
            val expected = mediaContentDetails.toUiMediaContentDetails()

            `when`(getMediaContentDetails.invoke(anyInt())).thenReturn(
                Result.success(
                    mediaContentDetails
                )
            )

            viewModel.loadMediaContentDetails(mediaContentId = 1)
            advanceUntilIdle()

            Assert.assertEquals(expected, viewModel.mediaContentDetails.value)
            Assert.assertEquals(
                false,
                viewModel.showCircularProgressBarEvent.value?.getContentIfNotHandled()
            )
        }

    @Test
    fun `given media content id when loadMediaContentDetails fails then circular progress bar disappears`() =
        runTest {
            `when`(getMediaContentDetails.invoke(anyInt())).thenReturn(Result.failure(Exception()))

            viewModel.loadMediaContentDetails(mediaContentId = 1)
            advanceUntilIdle()

            Assert.assertEquals(
                false,
                viewModel.showCircularProgressBarEvent.value?.getContentIfNotHandled()
            )
        }

    @Test
    fun `given favorite media content id, then load the favorite media content details`() =
        runTest {
            val mediaContentDetails = MovieDetails(
                id = 1,
                title = "Title",
                year = 2020,
                imageUrl = "imageUrl",
                description = "description",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
            val expected = mediaContentDetails.toUiMediaContentDetails()

            `when`(getFavoriteMediaContentDetails.invoke(anyInt())).thenReturn(mediaContentDetails)

            viewModel.loadFavoriteMediaContentDetails(mediaContentId = 1)
            advanceUntilIdle()

            Assert.assertEquals(expected, viewModel.mediaContentDetails.value)
            Assert.assertEquals(
                false,
                viewModel.showCircularProgressBarEvent.value?.getContentIfNotHandled()
            )
        }

    @Test
    fun `When clicking on favorite, insert favorite media content`() = runTest {
        val mediaContentDetails = MovieDetails(
            id = 1,
            title = "Title",
            year = 2020,
            imageUrl = "imageUrl",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )

        `when`(getFavoriteMediaContentDetails.invoke(anyInt())).thenReturn(mediaContentDetails)
        viewModel.loadFavoriteMediaContentDetails(mediaContentId = 1)
        advanceUntilIdle()
        viewModel.onClickFavorite()
        advanceUntilIdle()

        verify(insertFavoriteMediaContent, Mockito.times(1)).invoke(mediaContentDetails)
    }

    @Test
    fun `When clicking on delete, delete favorite media content`() = runTest {
        val mediaContentId = 1
        val mediaContentDetails = MovieDetails(
            id = 1,
            title = "Title",
            year = 2020,
            imageUrl = "imageUrl",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )

        `when`(getFavoriteMediaContentDetails.invoke(anyInt())).thenReturn(mediaContentDetails)
        viewModel.loadFavoriteMediaContentDetails(mediaContentId)
        advanceUntilIdle()
        viewModel.updateMediaContentId(mediaContentId)
        advanceUntilIdle()
        viewModel.onClickDelete()
        advanceUntilIdle()

        verify(deleteFavoriteMediaContent, Mockito.times(1)).invoke(mediaContentId)
    }
}