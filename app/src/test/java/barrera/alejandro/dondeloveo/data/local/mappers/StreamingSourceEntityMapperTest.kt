package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceEntity
import barrera.alejandro.dondeloveo.domain.model.StreamingSource
import org.junit.Assert.assertEquals
import org.junit.Test

class StreamingSourceEntityMapperTest {
    @Test
    fun `test StreamingSourceEntity to StreamingSource mapping`() {
        val streamingSourceEntity = StreamingSourceEntity(
            id = 0,
            mediaContentId = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val streamingSource = streamingSourceEntity.toStreamingSource()

        assertEquals(1, streamingSource.id)
        assertEquals("Name", streamingSource.name)
        assertEquals("imageUrl", streamingSource.imageUrl)
        assertEquals("webUrl", streamingSource.webUrl)
    }

    @Test
    fun `test StreamingSource to StreamingSourceEntity mapping`() {
        val streamingSource = StreamingSource(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            webUrl = "webUrl"
        )
        val streamingSourceEntity = streamingSource.toStreamingSourceEntity(mediaContentId = 2)

        assertEquals(0, streamingSourceEntity.id)
        assertEquals(2, streamingSourceEntity.mediaContentId)
        assertEquals("Name", streamingSourceEntity.name)
        assertEquals("imageUrl", streamingSourceEntity.imageUrl)
        assertEquals("webUrl", streamingSourceEntity.webUrl)
    }
}