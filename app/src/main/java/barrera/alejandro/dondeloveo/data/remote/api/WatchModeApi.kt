package barrera.alejandro.dondeloveo.data.remote.api

import barrera.alejandro.dondeloveo.data.remote.constants.WATCHMODE_API_KEY
import barrera.alejandro.dondeloveo.data.remote.dto.MediaContentDto
import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceLogoUrlDto
import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {

    companion object {
        const val BASE_URL = "https://api.watchmode.com/v1/"
        // Add your own Watchmode API key. For more info, visit https://api.watchmode.com
        const val API_KEY = WATCHMODE_API_KEY
        const val SEARCH_BY_TITLE = 2
    }

    @GET("sources")
    suspend fun getAllStreamingSourceLogos(
        @Query("apiKey") apiKey: String = API_KEY
    ): List<StreamingSourceLogoUrlDto>

    @GET("autocomplete-search")
    suspend fun searchByTitle(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("search_type") searchType: Int = SEARCH_BY_TITLE,
        @Query("search_value") query: String
    ): SearchByTitleResponseDto

    @GET("title/{media_content_id}/details")
    suspend fun getDetails(
        @Path("media_content_id") mediaContentId: Int,
        @Query("language") language: String = "ES",
        @Query("apiKey") apiKey: String = API_KEY
    ): MediaContentDto

    @GET("title/{media_content_id}/sources")
    suspend fun getStreamingSources(
        @Path("media_content_id") mediaContentId: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): List<StreamingSourceDto>

    @GET("title/{media_content_id}/cast-crew")
    suspend fun getTeamMembers(
        @Path("media_content_id") mediaContentId: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): List<TeamMemberDto>
}