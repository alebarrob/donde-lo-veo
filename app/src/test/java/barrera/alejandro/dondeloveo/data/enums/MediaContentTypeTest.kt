package barrera.alejandro.dondeloveo.data.enums

import org.junit.Test
import org.junit.Assert.assertEquals

class MediaContentTypeTest {

    @Test
    fun `test fromValue given 'movie' returns MOVIE`() {
        val expected = MediaContentType.MOVIE
        val actual = MediaContentType.fromValue("movie")
        assertEquals(expected, actual)
    }

    @Test
    fun `test fromValue given 'tv' returns TV`() {
        val expected = MediaContentType.TV
        val actual = MediaContentType.fromValue("tv")
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test fromValue given unknown value throws IllegalArgumentException`() {
        MediaContentType.fromValue("unknown")
    }
}