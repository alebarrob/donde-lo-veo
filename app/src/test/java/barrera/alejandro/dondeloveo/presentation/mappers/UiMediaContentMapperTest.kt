package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMovieDetails
import barrera.alejandro.dondeloveo.presentation.model.UiMovieOverview
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesDetails
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesOverview
import org.junit.Assert.assertEquals
import org.junit.Test

class UiMediaContentMapperTest {
    @Test
    fun `test MediaContent to UiMediaContentOverview mapper for movies`() {
        val movieOverview = MovieOverview(
            id = 1,
            title = "Test Title",
            year = 2021,
            imageUrl = "Test Image"
        )
        val expected = UiMovieOverview(
            id = 1,
            title = "Test Title",
            year = "2021",
            imageUrl = "Test Image"
        )
        val actual = movieOverview.toUiMediaContentOverview()

        assertEquals(expected, actual)
    }

    @Test
    fun `test MediaContent to UiMediaContentOverview mapper for series`() {
        val seriesOverview = SeriesOverview(
            id = 1,
            title = "Test Title",
            year = 2021,
            imageUrl = "Test Image"
        )
        val expected = UiSeriesOverview(
            id = 1,
            title = "Test Title",
            year = "2021",
            imageUrl = "Test Image"
        )
        val actual = seriesOverview.toUiMediaContentOverview()

        assertEquals(expected, actual)
    }

    @Test
    fun `test MediaContent to UiMediaContentDetails mapper for movies`() {
        val movieDetails = MovieDetails(
            id = 1,
            title = "Test Title",
            year = 2021,
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val expected = UiMovieDetails(
            id = 1,
            title = "Test Title",
            year = "2021",
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()

        )
        val actual = movieDetails.toUiMediaContentDetails()

        assertEquals(expected, actual)
    }

    @Test
    fun `test MediaContent to UiMediaContentDetails mapper for series`() {
        val seriesDetails = SeriesDetails(
            id = 1,
            title = "Test Title",
            year = 2021,
            endYear = 2022,
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val expected = UiSeriesDetails(
            id = 1,
            title = "Test Title",
            year = "2021",
            endYear = "2022",
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()

        )
        val actual = seriesDetails.toUiMediaContentDetails()

        assertEquals(expected, actual)
    }

    @Test
    fun `test UiMediaContent to MediaContentDetails mapper for movies`() {
        val uiMovieDetails = UiMovieDetails(
            id = 1,
            title = "Test Title",
            year = "2021",
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val expected = MovieDetails(
            id = 1,
            title = "Test Title",
            year = 2021,
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()

        )
        val actual = uiMovieDetails.toMediaContentDetails()

        assertEquals(expected, actual)
    }

    @Test
    fun `test UiMediaContent to MediaContentDetails mapper for series`() {
        val uiMovieDetails = UiSeriesDetails(
            id = 1,
            title = "Test Title",
            year = "2021",
            endYear = "2022",
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val expected = SeriesDetails(
            id = 1,
            title = "Test Title",
            year = 2021,
            endYear = 2022,
            imageUrl = "Test Image",
            description = "description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()

        )
        val actual = uiMovieDetails.toMediaContentDetails()

        assertEquals(expected, actual)
    }
}