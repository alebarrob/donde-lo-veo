package barrera.alejandro.dondeloveo.presentation.model

data class UiMovieOverview(
    override val id: Int,
    override val title: String,
    override val year: String,
    override val imageUrl: String?
) : UiMediaContentOverview
