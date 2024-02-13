package barrera.alejandro.dondeloveo.data.remote.dto

import com.squareup.moshi.Json

data class StreamingSourceDto(
    @field:Json(name = "source_id")
    val sourceId: Int,

    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "web_url")
    val webUrl: String?
)