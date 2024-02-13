package barrera.alejandro.dondeloveo.data.remote.dto

import com.squareup.moshi.Json

data class TeamMemberDto(
    @field:Json(name = "person_id")
    val personId: Int,

    @field:Json(name = "type")
    val type: String?,

    @field:Json(name = "full_name")
    val fullName: String?,

    @field:Json(name = "headshot_url")
    val headshotUrl: String?,

    @field:Json(name = "role")
    val role: String?
)
