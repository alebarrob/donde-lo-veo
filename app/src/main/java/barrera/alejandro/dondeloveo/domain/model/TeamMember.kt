package barrera.alejandro.dondeloveo.domain.model

interface TeamMember {
    val id: Int
    val name: String
    val imageUrl: String?
    val role: String
}