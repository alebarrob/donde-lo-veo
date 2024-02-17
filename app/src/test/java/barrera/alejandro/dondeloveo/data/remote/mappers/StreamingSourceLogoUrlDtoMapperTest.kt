package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceLogoUrlDto
import org.junit.Assert
import org.junit.Test

class StreamingSourceLogoUrlDtoMapperTest {
    @Test
    fun `test StreamingSourceLogoUrlDto to StreamingSourceLogoUrlEntity mapping`() {
        val streamingSourceLogoUrlDto = StreamingSourceLogoUrlDto(
            id = 1,
            logo100Px = "imageUrl"
        )
        val streamingSourceLogoUrlEntity =
            streamingSourceLogoUrlDto.toStreamingSourceLogoUrlEntity()

        Assert.assertEquals(1, streamingSourceLogoUrlEntity.id)
        Assert.assertEquals("imageUrl", streamingSourceLogoUrlEntity.imageUrl)
    }
}