package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.CrewMemberEntity
import barrera.alejandro.dondeloveo.domain.model.CrewMember

fun CrewMemberEntity.toCrewMember(): CrewMember {
    return CrewMember(
        id = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}

fun CrewMember.toCrewMemberEntity(mediaContentId: Int): CrewMemberEntity {
    return CrewMemberEntity(
        id = 0,
        mediaContentId = mediaContentId,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}