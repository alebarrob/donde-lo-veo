package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import org.junit.Assert.assertEquals
import org.junit.Test

class StreamingSourceDtoMapperTest {
    @Test
    fun `test StreamingSourceDto to StreamingSource mapping`() {
        val streamingSourceDto = StreamingSourceDto(
            sourceId = 1,
            name = "Name",
            webUrl = "http://webUrl.com"
        )
        val streamingSource = streamingSourceDto.toStreamingSource("http://imageUrl.com")

        assertEquals(1, streamingSource.id)
        assertEquals("Name", streamingSource.name)
        assertEquals("http://imageUrl.com", streamingSource.imageUrl)
        assertEquals("http://webUrl.com", streamingSource.webUrl)
    }
}