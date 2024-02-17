package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toFavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentOverview
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesOverview
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoriteMediaContentEntityMapperTest {
    @Test
    fun `test FavoriteMediaContentEntity to MediaContentOverview mapping for movies`() {
        val favoriteMediaContentEntity = FavoriteMediaContentEntity(
            id = 1,
            title = "Title",
            year = 2020,
            endYear = null,
            imageUrl = "imageUrl",
            description = "Description",
            type = "movie"
        )
        val mediaContentOverview = favoriteMediaContentEntity.toMediaContentOverview()

        assertTrue(mediaContentOverview is MovieOverview)
        assertEquals(1, mediaContentOverview.id)
        assertEquals("Title", mediaContentOverview.title)
        assertEquals(2020, mediaContentOverview.year)
        assertEquals("imageUrl", mediaContentOverview.imageUrl)
    }

    @Test
    fun `test FavoriteMediaContentEntity to MediaContentOverview mapping for series`() {
        val favoriteMediaContentEntity = FavoriteMediaContentEntity(
            id = 1,
            title = "Title",
            year = 2020,
            endYear = 2023,
            imageUrl = "imageUrl",
            description = "Description",
            type = "tv"
        )
        val mediaContentOverview = favoriteMediaContentEntity.toMediaContentOverview()

        assertTrue(mediaContentOverview is SeriesOverview)
        assertEquals(1, mediaContentOverview.id)
        assertEquals("Title", mediaContentOverview.title)
        assertEquals(2020, mediaContentOverview.year)
        assertEquals("imageUrl", mediaContentOverview.imageUrl)
    }

    @Test
    fun `test MediaContentDetails to FavoriteMediaContentEntity mapping for movies`() {
        val movieDetails = MovieDetails(
            id = 1,
            title = "Title",
            year = 2020,
            imageUrl = "imageUrl",
            description = "Description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val favoriteMediaContentEntity = movieDetails.toFavoriteMediaContentEntity()

        assertEquals(1, favoriteMediaContentEntity.id)
        assertEquals("Title", favoriteMediaContentEntity.title)
        assertEquals(2020, favoriteMediaContentEntity.year)
        assertEquals(null, favoriteMediaContentEntity.endYear)
        assertEquals("imageUrl", favoriteMediaContentEntity.imageUrl)
        assertEquals("Description", favoriteMediaContentEntity.description)
        assertEquals("movie", favoriteMediaContentEntity.type)
    }

    @Test
    fun `test MediaContentDetails to FavoriteMediaContentEntity mapping for series`() {
        val seriesDetails = SeriesDetails(
            id = 1,
            title = "Title",
            year = 2020,
            endYear = 2021,
            imageUrl = "imageUrl",
            description = "Description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val favoriteMediaContentEntity = seriesDetails.toFavoriteMediaContentEntity()

        assertEquals(1, favoriteMediaContentEntity.id)
        assertEquals("Title", favoriteMediaContentEntity.title)
        assertEquals(2020, favoriteMediaContentEntity.year)
        assertEquals(2021, favoriteMediaContentEntity.endYear)
        assertEquals("imageUrl", favoriteMediaContentEntity.imageUrl)
        assertEquals("Description", favoriteMediaContentEntity.description)
        assertEquals("tv", favoriteMediaContentEntity.type)
    }
}