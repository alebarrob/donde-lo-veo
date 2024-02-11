package barrera.alejandro.dondeloveo.domain.model

interface MediaContentDetails : MediaContent {
    val description: String
    val streamingSources: List<StreamingSource>
    val crew: List<CrewMember>
    val cast: List<CastMember>
}