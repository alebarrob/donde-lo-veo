package barrera.alejandro.dondeloveo.data.enums

enum class MediaContentType {
    MOVIE,
    TV;

    companion object {
        fun fromValue(value: String?): MediaContentType {
            return when (value) {
                "movie" -> MOVIE
                "tv" -> TV
                else -> throw IllegalArgumentException("Wrong value: $value")
            }
        }
    }
}