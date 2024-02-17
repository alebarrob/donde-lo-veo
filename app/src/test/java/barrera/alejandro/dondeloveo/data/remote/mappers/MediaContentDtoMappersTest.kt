package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.MediaContentDto
import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import barrera.alejandro.dondeloveo.domain.model.StreamingSource
import org.junit.Assert
import org.junit.Test

class MediaContentDtoMappersTest {
    @Test
    fun `test MediaContentDto to MediaContentDetails mapping for movies`() {
        val mediaContentDto = MediaContentDto(
            id = 1,
            title = "Title",
            plotOverview = "plotOverview",
            poster = "imageUrl",
            year = 2020,
            endYear = null,
            tmdbType = "movie"
        )
        val mediaContentDetails = mediaContentDto.toMediaContentDetails(emptyMap(), emptyList())

        Assert.assertEquals(1, mediaContentDetails.id)
        Assert.assertEquals("Title", mediaContentDetails.title)
        Assert.assertEquals("plotOverview", mediaContentDetails.description)
        Assert.assertEquals("imageUrl", mediaContentDetails.imageUrl)
        Assert.assertEquals(2020, mediaContentDetails.year)
        Assert.assertEquals("imageUrl", mediaContentDetails.imageUrl)
        Assert.assertEquals(emptyList<StreamingSource>(), mediaContentDetails.streamingSources)
        Assert.assertEquals(emptyList<CrewMember>(), mediaContentDetails.crew)
        Assert.assertEquals(emptyList<CastMember>(), mediaContentDetails.cast)
    }

    @Test
    fun `test MediaContentDto to MediaContentDetails mapping for series`() {
        val mediaContentDto = MediaContentDto(
            id = 1,
            title = "Title",
            plotOverview = "plotOverview",
            poster = "imageUrl",
            year = 2020,
            endYear = 2023,
            tmdbType = "tv"
        )
        val mediaContentDetails = mediaContentDto.toMediaContentDetails(emptyMap(), emptyList())

        Assert.assertEquals(1, mediaContentDetails.id)
        Assert.assertEquals("Title", mediaContentDetails.title)
        Assert.assertEquals("plotOverview", mediaContentDetails.description)
        Assert.assertEquals("imageUrl", mediaContentDetails.imageUrl)
        Assert.assertEquals(2020, mediaContentDetails.year)
        Assert.assertEquals(2023, (mediaContentDetails as SeriesDetails).endYear)
        Assert.assertEquals("imageUrl", mediaContentDetails.imageUrl)
        Assert.assertEquals(emptyList<StreamingSource>(), mediaContentDetails.streamingSources)
        Assert.assertEquals(emptyList<CrewMember>(), mediaContentDetails.crew)
        Assert.assertEquals(emptyList<CastMember>(), mediaContentDetails.cast)
    }
}