package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.CastMemberEntity
import barrera.alejandro.dondeloveo.domain.model.CastMember

fun CastMemberEntity.toCastMember(): CastMember {
    return CastMember(
        id = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}
fun CastMember.toCastMemberEntity(mediaContentId: Int): CastMemberEntity {
    return CastMemberEntity(
        id = 0,
        mediaContentId = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}