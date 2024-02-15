package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceEntity
import barrera.alejandro.dondeloveo.domain.model.StreamingSource

fun StreamingSourceEntity.toStreamingSource(): StreamingSource {
    return StreamingSource(
        id = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        webUrl = webUrl
    )
}

fun StreamingSource.toStreamingSourceEntity(mediaContentId: Int): StreamingSourceEntity {
    return StreamingSourceEntity(
        id = 0,
        mediaContentId = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        webUrl = webUrl
    )
}