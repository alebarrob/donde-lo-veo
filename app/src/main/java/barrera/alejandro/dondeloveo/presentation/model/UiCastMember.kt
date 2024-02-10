package barrera.alejandro.dondeloveo.presentation.model

data class UiCastMember(
    override val id: Int,
    override val name: String,
    override val imageUrl: String?,
    override val role: String
) : UiTeamMember
