package barrera.alejandro.dondeloveo.data.remote.enums

enum class TeamMemberTypes {
    CREW,
    CAST;

    companion object {
        fun fromValue(value: String?): TeamMemberTypes {
            return when (value) {
                "Crew" -> CREW
                "Cast" -> CAST
                else -> throw IllegalArgumentException("Wrong value: $value")
            }
        }
    }
}