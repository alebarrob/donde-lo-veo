package barrera.alejandro.dondeloveo.presentation.model

data class UiCrewMember(
    override val id: Int,
    override val name: String,
    override val imageUrl: String?,
    override val role: String
) : UiTeamMember
