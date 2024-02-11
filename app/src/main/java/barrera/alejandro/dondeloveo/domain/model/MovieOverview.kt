package barrera.alejandro.dondeloveo.domain.model

data class MovieOverview(
    override val id: Int,
    override val title: String,
    override val year: Int,
    override val imageUrl: String?
) : MediaContentOverview
