package barrera.alejandro.dondeloveo.data.remote.dto

import com.squareup.moshi.Json

data class StreamingSourceLogoUrlDto(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "logo_100px")
    val logo100Px: String?
)