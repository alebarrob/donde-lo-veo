package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.StreamingSource
import barrera.alejandro.dondeloveo.presentation.model.UiStreamingSource
import org.junit.Assert
import org.junit.Test

class UiStreamingSourceMapperTest {
    @Test
    fun `test UiStreamingSource to StreamingSource mapper`() {
        val uiStreamingSource = UiStreamingSource(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val expected = StreamingSource(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val actual = uiStreamingSource.toStreamingSource()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test StreamingSource to UiStreamingSource mapper`() {
        val streamingSource = StreamingSource(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val expected = UiStreamingSource(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val actual = streamingSource.toUiStreamingSource()

        Assert.assertEquals(expected, actual)
    }
}