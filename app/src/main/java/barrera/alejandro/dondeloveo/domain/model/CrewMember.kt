package barrera.alejandro.dondeloveo.domain.model

data class CrewMember(
    override val id: Int,
    override val name: String,
    override val imageUrl: String?,
    override val role: String
) : TeamMember
