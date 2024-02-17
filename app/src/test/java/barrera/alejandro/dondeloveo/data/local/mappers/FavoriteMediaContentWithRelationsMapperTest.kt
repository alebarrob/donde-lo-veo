package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentWithRelations
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.StreamingSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoriteMediaContentWithRelationsMapperTest {
    @Test
    fun `test FavoriteMediaContentWithRelations to MediaContentDetails mapping for movies`() {
        val favoriteMediaContentWithRelations = FavoriteMediaContentWithRelations(
            favoriteMediaContent = FavoriteMediaContentEntity(
                id = 1,
                title = "Title",
                year = 2020,
                endYear = null,
                imageUrl = "imageUrl",
                description = "Description",
                type = "movie"
            ),
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val mediaContentDetails = favoriteMediaContentWithRelations.toMediaContentDetails()

        assertTrue(mediaContentDetails is MovieDetails)
        assertEquals(1, mediaContentDetails.id)
        assertEquals("Title", mediaContentDetails.title)
        assertEquals(2020, mediaContentDetails.year)
        assertEquals("imageUrl", mediaContentDetails.imageUrl)
        assertEquals("Description", mediaContentDetails.description)
        assertEquals(emptyList<StreamingSource>(), mediaContentDetails.streamingSources)
        assertEquals(emptyList<CrewMember>(), mediaContentDetails.crew)
        assertEquals(emptyList<CastMember>(), mediaContentDetails.cast)
    }

    @Test
    fun `test FavoriteMediaContentWithRelations to MediaContentDetails mapping for series`() {
        val favoriteMediaContentWithRelations = FavoriteMediaContentWithRelations(
            favoriteMediaContent = FavoriteMediaContentEntity(
                id = 1,
                title = "Title",
                year = 2020,
                endYear = 2023,
                imageUrl = "imageUrl",
                description = "Description",
                type = "tv"
            ),
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val mediaContentDetails = favoriteMediaContentWithRelations.toMediaContentDetails()

        assertTrue(mediaContentDetails is SeriesDetails)
        assertEquals(1, mediaContentDetails.id)
        assertEquals("Title", mediaContentDetails.title)
        assertEquals(2020, mediaContentDetails.year)
        assertEquals(2023, (mediaContentDetails as SeriesDetails).endYear)
        assertEquals("imageUrl", mediaContentDetails.imageUrl)
        assertEquals("Description", mediaContentDetails.description)
        assertEquals(emptyList<StreamingSource>(), mediaContentDetails.streamingSources)
        assertEquals(emptyList<CrewMember>(), mediaContentDetails.crew)
        assertEquals(emptyList<CastMember>(), mediaContentDetails.cast)
    }
}