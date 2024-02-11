package barrera.alejandro.dondeloveo.domain.model

data class CastMember(
    override val id: Int,
    override val name: String,
    override val imageUrl: String?,
    override val role: String
) : TeamMember
