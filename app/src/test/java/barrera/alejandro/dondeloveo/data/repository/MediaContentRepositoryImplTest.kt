package barrera.alejandro.dondeloveo.data.repository

import barrera.alejandro.dondeloveo.data.local.dao.CastMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.CrewMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.FavoriteMediaContentDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.entity.FavoriteMediaContentWithRelations
import barrera.alejandro.dondeloveo.data.local.mappers.toCastMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toCrewMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toFavoriteMediaContentEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.data.local.mappers.toMediaContentOverview
import barrera.alejandro.dondeloveo.data.local.mappers.toStreamingSourceEntity
import barrera.alejandro.dondeloveo.data.remote.api.WatchmodeApi
import barrera.alejandro.dondeloveo.data.remote.dto.MediaContentDto
import barrera.alejandro.dondeloveo.data.remote.dto.SearchByTitleResponseDto
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceDto
import barrera.alejandro.dondeloveo.data.remote.dto.StreamingSourceLogoUrlDto
import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import barrera.alejandro.dondeloveo.data.remote.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.data.remote.mappers.toMediaContentOverview
import barrera.alejandro.dondeloveo.data.remote.mappers.toStreamingSourceLogoUrlEntity
import barrera.alejandro.dondeloveo.domain.model.SeriesDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MediaContentRepositoryImplTest {
    private lateinit var mediaContentRepositoryImpl: MediaContentRepositoryImpl

    private val mockApi: WatchmodeApi = mock()
    private val mockStreamingSourceLogoUrlDao: StreamingSourceLogoUrlDao = mock()
    private val mockStreamingSourceDao: StreamingSourceDao = mock()
    private val mockCrewMemberDao: CrewMemberDao = mock()
    private val mockCastMemberDao: CastMemberDao = mock()
    private val mockFavoriteMediaContentDao: FavoriteMediaContentDao = mock()

    @Before
    fun setup() {
        mediaContentRepositoryImpl = MediaContentRepositoryImpl(
            mockApi,
            mockStreamingSourceLogoUrlDao,
            mockStreamingSourceDao,
            mockCrewMemberDao,
            mockCastMemberDao,
            mockFavoriteMediaContentDao
        )
    }

    @Test
    fun `test saveStreamingSourceLogosUrl saves logos correctly`(): Unit = runTest {
        val expectedApiResult = listOf<StreamingSourceLogoUrlDto>(mock(), mock())
        val expectedEntities = expectedApiResult.map { it.toStreamingSourceLogoUrlEntity() }

        `when`(mockApi.getAllStreamingSourceLogos()).thenReturn(expectedApiResult)

        mediaContentRepositoryImpl.saveStreamingSourceLogosUrl()

        verify(mockStreamingSourceLogoUrlDao).upsertStreamingSourceLogosUrl(expectedEntities)
    }

    @Test
    fun `test searchByTitle gets correct media contents overview`() = runTest {
        val expectedApiResult = SearchByTitleResponseDto(
            results = listOf(
                SearchByTitleResponseDto.SearchResultDto(
                    id = 0,
                    name = "Name",
                    year = 2020,
                    imageUrl = "imageUrl",
                    tmdbType = "movie"
                ),
                SearchByTitleResponseDto.SearchResultDto(
                    id = 0,
                    name = "Name",
                    year = 2020,
                    imageUrl = "imageUrl",
                    tmdbType = "tv"
                )
            )
        )
        val expected = expectedApiResult.results.map { it.toMediaContentOverview() }

        `when`(mockApi.searchByTitle(anyString(), anyInt(), anyString())).thenReturn(
            expectedApiResult
        )

        val actual = mediaContentRepositoryImpl.searchByTitle("query")

        assertEquals(expected, actual.getOrNull())
    }

    @Test
    fun `test getMediaContentDetails gets correct media content details`() = runTest {
        val expectedApiDetailsResult = MediaContentDto(
            id = 1,
            title = "Title",
            plotOverview = "plotOverview",
            poster = "poster",
            year = 2020,
            endYear = 2023,
            tmdbType = "tv"
        )
        val expectedApiStreamingSourcesResult = listOf(
            StreamingSourceDto(
                sourceId = 1,
                name = "Name",
                webUrl = "webUrl"
            )
        )
        val expectedApiTeamMembersResult = listOf(
            TeamMemberDto(
                personId = 1,
                type = "Crew",
                fullName = "fullName",
                headshotUrl = "headshotUrl",
                role = "role"
            )
        )
        val expectedStreamingSourceLogoUrlDaoResult = "imageUrl"
        val expected = expectedApiDetailsResult.toMediaContentDetails(
            streamingSourceDtosWithImageUrls = mapOf(
                StreamingSourceDto(
                    sourceId = 1,
                    name = "Name",
                    webUrl = "webUrl"
                ) to expectedStreamingSourceLogoUrlDaoResult
            ),
            teamMemberDtos = expectedApiTeamMembersResult,
        )

        `when`(mockApi.getDetails(anyInt(), anyString(), anyString())).thenReturn(
            expectedApiDetailsResult
        )
        `when`(mockApi.getStreamingSources(anyInt(), anyString())).thenReturn(
            expectedApiStreamingSourcesResult
        )
        `when`(mockApi.getTeamMembers(anyInt(), anyString())).thenReturn(
            expectedApiTeamMembersResult
        )
        `when`(mockStreamingSourceLogoUrlDao.getStreamingSourceLogoUrlById(anyInt())).thenReturn(
            expectedStreamingSourceLogoUrlDaoResult
        )

        val actual = mediaContentRepositoryImpl.getMediaContentDetails(mediaContentId = 1)

        assertEquals(expected, actual.getOrNull())
    }

    @Test
    fun `test insertFavoriteMediaContent correctly inserts data`() = runTest {
        val seriesDetails = SeriesDetails(
            id = 1,
            title = "Title",
            year = 2020,
            endYear = 2021,
            imageUrl = "imageUrl",
            description = "Description",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )

        mediaContentRepositoryImpl.insertFavoriteMediaContent(seriesDetails)

        verify(mockFavoriteMediaContentDao).insertFavoriteMediaContent(
            seriesDetails.toFavoriteMediaContentEntity()
        )

        verify(mockCrewMemberDao).insertCrewMembers(
            seriesDetails.crew.map { it.toCrewMemberEntity(seriesDetails.id) }
        )

        verify(mockCastMemberDao).insertCastMembers(
            seriesDetails.cast.map { it.toCastMemberEntity(seriesDetails.id) }
        )

        verify(mockStreamingSourceDao).insertStreamingSources(
            seriesDetails.streamingSources.map {
                it.toStreamingSourceEntity(
                    seriesDetails.id
                )
            }
        )
    }

    @Test
    fun `test getAllFavoriteMediaContentOverview returns correct values`() = runTest {
        val favoriteMediaContentWithRelationsList = listOf(
            FavoriteMediaContentWithRelations(
                FavoriteMediaContentEntity(
                    id = 1,
                    title = "Title1",
                    year = 2020,
                    endYear = 2021,
                    imageUrl = "imageUrl1",
                    description = "Description1",
                    type = "tv"
                ),
                emptyList(),
                emptyList(),
                emptyList()
            ),
            FavoriteMediaContentWithRelations(
                FavoriteMediaContentEntity(
                    id = 2,
                    title = "Title2",
                    year = 2020,
                    endYear = 2021,
                    imageUrl = "imageUrl2",
                    description = "Description2",
                    type = "tv"
                ),
                emptyList(),
                emptyList(),
                emptyList()
            )
        )
        val expected =
            favoriteMediaContentWithRelationsList.map { favoriteMediaContentWithRelations ->
                favoriteMediaContentWithRelations.favoriteMediaContent.toMediaContentOverview()
            }

        `when`(mockFavoriteMediaContentDao.getAllFavoriteMediaContent()).thenReturn(
            favoriteMediaContentWithRelationsList
        )

        val actual = mediaContentRepositoryImpl.getAllFavoriteMediaContentOverview()

        assertEquals(expected, actual)
    }

    @Test
    fun `test getFavoriteMediaContentDetails returns correct MediaContentDetails`() = runTest {
        val favoriteMediaContentWithRelations = FavoriteMediaContentWithRelations(
            FavoriteMediaContentEntity(
                id = 1,
                title = "Title",
                year = 2020,
                endYear = 2023,
                imageUrl = "imageUrl",
                description = "description",
                type = "tv"
            ),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val expected = favoriteMediaContentWithRelations.toMediaContentDetails()

        `when`(
            mockFavoriteMediaContentDao.getFavoriteMediaContent(anyInt())
        ).thenReturn(favoriteMediaContentWithRelations)

        val actual = mediaContentRepositoryImpl.getFavoriteMediaContentDetails(mediaContentId = 1)

        assertEquals(expected, actual)
    }

    @Test
    fun `test deleteFavoriteMediaContent invokes correct DAO methods`() = runTest {
        val testId = 1

        mediaContentRepositoryImpl.deleteFavoriteMediaContent(testId)

        verify(mockFavoriteMediaContentDao).deleteFavoriteMediaContent(testId)
        verify(mockCrewMemberDao).deleteCrewMember(testId)
        verify(mockCastMemberDao).deleteCastMember(testId)
        verify(mockStreamingSourceDao).deleteStreamingSources(testId)
    }
}