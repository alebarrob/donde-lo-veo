package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import barrera.alejandro.dondeloveo.data.remote.enums.TeamMemberTypes
import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.TeamMember

fun TeamMemberDto.toTeamMember(): TeamMember {
    return when (TeamMemberTypes.fromValue(type)) {
        TeamMemberTypes.CREW -> this.toCrewMember()
        TeamMemberTypes.CAST -> this.toCastMember()
    }
}

private fun TeamMemberDto.toCrewMember(): TeamMember {
    return CrewMember(
        id = personId,
        name = fullName ?: "Name not available",
        imageUrl = headshotUrl,
        role = role ?: "Role not available"
    )
}

private fun TeamMemberDto.toCastMember(): TeamMember {
    return CastMember(
        id = personId,
        name = fullName ?: "Name not available",
        imageUrl = headshotUrl,
        role = role ?: "Role not available"
    )
}