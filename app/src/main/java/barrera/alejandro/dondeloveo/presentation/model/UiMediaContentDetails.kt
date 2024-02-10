package barrera.alejandro.dondeloveo.presentation.model

interface UiMediaContentDetails : UiMediaContent {
    val description: String
    val streamingSources: List<UiStreamingSource>
    val crew: List<UiCrewMember>
    val cast: List<UiCastMember>
}