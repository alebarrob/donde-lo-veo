package barrera.alejandro.dondeloveo.data.remote.dto

import com.squareup.moshi.Json

data class SearchByTitleResponseDto(
    @field:Json(name = "results")
    val results: List<SearchResultDto>
) {
    data class SearchResultDto(
        @field:Json(name = "id")
        val id: Int,

        @field:Json(name = "name")
        val name: String?,

        @field:Json(name = "year")
        val year: Int?,

        @field:Json(name = "image_url")
        val imageUrl: String?,

        @field:Json(name = "tmdb_type")
        val tmdbType: String?
    )
}