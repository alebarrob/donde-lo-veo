package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.StreamingSource
import barrera.alejandro.dondeloveo.presentation.model.UiStreamingSource

fun UiStreamingSource.toStreamingSource(): StreamingSource {
    return StreamingSource(
        id = id,
        name = name,
        imageUrl = imageUrl,
        webUrl = webUrl
    )
}

fun StreamingSource.toUiStreamingSource(): UiStreamingSource {
    return UiStreamingSource(
        id = id,
        name = name,
        imageUrl = imageUrl,
        webUrl = webUrl
    )
}