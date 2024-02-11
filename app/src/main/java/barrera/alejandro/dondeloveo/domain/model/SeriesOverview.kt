package barrera.alejandro.dondeloveo.domain.model

data class SeriesOverview(
    override val id: Int,
    override val title: String,
    override val year: Int,
    override val imageUrl: String?
) : MediaContentOverview
