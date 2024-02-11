package barrera.alejandro.dondeloveo.domain.model

data class MovieDetails(
    override val id: Int,
    override val title: String,
    override val year: Int,
    override val imageUrl: String?,
    override val description: String,
    override val streamingSources: List<StreamingSource>,
    override val crew: List<CrewMember>,
    override val cast: List<CastMember>
) : MediaContentDetails
