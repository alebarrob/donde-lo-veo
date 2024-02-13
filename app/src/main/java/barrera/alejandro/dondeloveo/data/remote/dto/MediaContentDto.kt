package barrera.alejandro.dondeloveo.data.remote.dto

import com.squareup.moshi.Json

data class MediaContentDto(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "title")
    val title: String?,

    @field:Json(name = "plot_overview")
    val plotOverview: String?,

    @field:Json(name = "poster")
    val poster: String?,

    @field:Json(name = "year")
    val year: Int?,

    @field:Json(name = "end_year")
    val endYear: Int?,

    @field:Json(name = "tmdb_type")
    val tmdbType: String?
)