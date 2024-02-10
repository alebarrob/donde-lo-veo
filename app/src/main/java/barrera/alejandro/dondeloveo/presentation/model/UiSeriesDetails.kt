package barrera.alejandro.dondeloveo.presentation.model

data class UiSeriesDetails(
    override val id: Int,
    override val title: String,
    override val year: String,
    val endYear: String,
    override val imageUrl: String?,
    override val description: String,
    override val streamingSources: List<UiStreamingSource>,
    override val crew: List<UiCrewMember>,
    override val cast: List<UiCastMember>
) : UiMediaContentDetails