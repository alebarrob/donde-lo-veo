package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.local.entity.StreamingSourceLogoUrlEntity
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceLogoUrlDto

fun StreamingSourceLogoUrlDto.toStreamingSourceLogoUrlEntity(): StreamingSourceLogoUrlEntity {
    return StreamingSourceLogoUrlEntity(
        id = id,
        imageUrl = logo100Px
    )
}