package barrera.alejandro.dondeloveo.domain.model

data class SeriesDetails(
    override val id: Int,
    override val title: String,
    override val year: Int,
    val endYear: Int?,
    override val imageUrl: String?,
    override val description: String,
    override val streamingSources: List<StreamingSource>,
    override val crew: List<CrewMember>,
    override val cast: List<CastMember>
) : MediaContentDetails
