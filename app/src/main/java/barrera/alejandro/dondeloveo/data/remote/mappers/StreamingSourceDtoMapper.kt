package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import barrera.alejandro.dondeloveo.domain.model.StreamingSource

fun StreamingSourceDto.toStreamingSource(imageUrl: String): StreamingSource {
    return StreamingSource(
        id = sourceId,
        name = name ?: "Name not available",
        imageUrl = imageUrl,
        webUrl = webUrl
    )
}