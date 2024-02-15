package barrera.alejandro.dondeloveo.data.enums

enum class TeamMemberType {
    CREW,
    CAST;

    companion object {
        fun fromValue(value: String?): TeamMemberType {
            return when (value) {
                "Crew" -> CREW
                "Cast" -> CAST
                else -> throw IllegalArgumentException("Wrong value: $value")
            }
        }
    }
}