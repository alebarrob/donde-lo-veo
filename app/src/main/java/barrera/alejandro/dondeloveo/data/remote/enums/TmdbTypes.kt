package barrera.alejandro.dondeloveo.data.remote.enums

enum class TmdbTypes {
    MOVIE,
    TV;

    companion object {
        fun fromValue(value: String?): TmdbTypes {
            return when (value) {
                "movie" -> MOVIE
                "tv" -> TV
                else -> throw IllegalArgumentException("Wrong value: $value")
            }
        }
    }
}