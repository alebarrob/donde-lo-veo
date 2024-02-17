package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import org.junit.Assert
import org.junit.Test

class SearchResultDtoMapperTest {
    @Test
    fun `test SearchResultDto to MediaContentOverview mapping for movies`() {
        val searchResultDto = SearchByTitleResponseDto.SearchResultDto(
            id = 1,
            name = "Title",
            year = 2020,
            imageUrl = "imageUrl",
            tmdbType = "movie"
        )
        val mediaContentOverview = searchResultDto.toMediaContentOverview()

        Assert.assertEquals(1, mediaContentOverview.id)
        Assert.assertEquals("Title", mediaContentOverview.title)
        Assert.assertEquals(2020, mediaContentOverview.year)
        Assert.assertEquals("imageUrl", mediaContentOverview.imageUrl)
    }

    @Test
    fun `test SearchResultDto to MediaContentOverview mapping for series`() {
        val searchResultDto = SearchByTitleResponseDto.SearchResultDto(
            id = 1,
            name = "Title",
            year = 2020,
            imageUrl = "imageUrl",
            tmdbType = "tv"
        )
        val mediaContentOverview = searchResultDto.toMediaContentOverview()

        Assert.assertEquals(1, mediaContentOverview.id)
        Assert.assertEquals("Title", mediaContentOverview.title)
        Assert.assertEquals(2020, mediaContentOverview.year)
        Assert.assertEquals("imageUrl", mediaContentOverview.imageUrl)
    }
}