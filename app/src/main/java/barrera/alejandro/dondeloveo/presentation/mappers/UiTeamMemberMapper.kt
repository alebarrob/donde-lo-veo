package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.domain.model.TeamMember
import barrera.alejandro.dondeloveo.presentation.model.UiCastMember
import barrera.alejandro.dondeloveo.presentation.model.UiCrewMember
import barrera.alejandro.dondeloveo.presentation.model.UiTeamMember

fun UiTeamMember.toTeamMember(): TeamMember {
    return when (this) {
        is UiCastMember -> this.toCastMember()
        is UiCrewMember -> this.toCrewMember()
        else -> throw IllegalStateException("Invalid MediaContent type.")
    }
}

private fun UiCastMember.toCastMember(): CastMember {
    return CastMember(
        id = id,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}

private fun UiCrewMember.toCrewMember(): CrewMember {
    return CrewMember(
        id = id,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}

fun TeamMember.toUiTeamMember(): UiTeamMember {
    return when (this) {
        is CastMember -> this.toUiCastMember()
        is CrewMember -> this.toUiCrewMember()
        else -> throw IllegalStateException("Invalid MediaContent type.")
    }
}

private fun CastMember.toUiCastMember(): UiCastMember {
    return UiCastMember(
        id = id,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}

private fun CrewMember.toUiCrewMember(): UiCrewMember {
    return UiCrewMember(
        id = id,
        name = name,
        imageUrl = imageUrl,
        role = role
    )
}