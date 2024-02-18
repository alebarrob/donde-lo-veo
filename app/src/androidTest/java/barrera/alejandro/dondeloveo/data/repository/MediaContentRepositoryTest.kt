package barrera.alejandro.dondeloveo.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import barrera.alejandro.dondeloveo.data.local.dao.CastMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.CrewMemberDao
import barrera.alejandro.dondeloveo.data.local.dao.FavoriteMediaContentDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceDao
import barrera.alejandro.dondeloveo.data.local.dao.StreamingSourceLogoUrlDao
import barrera.alejandro.dondeloveo.data.local.database.Database
import barrera.alejandro.dondeloveo.data.remote.api.WatchmodeApi
import barrera.alejandro.dondeloveo.domain.model.MovieDetails
import barrera.alejandro.dondeloveo.domain.model.MovieOverview
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(AndroidJUnit4::class)
class MediaContentRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: WatchmodeApi

    private lateinit var database: Database

    private lateinit var streamingSourceLogoUrlDao: StreamingSourceLogoUrlDao
    private lateinit var streamingSourceDao: StreamingSourceDao
    private lateinit var crewMemberDao: CrewMemberDao
    private lateinit var castMemberDao: CastMemberDao
    private lateinit var favoriteMediaContentDao: FavoriteMediaContentDao

    private lateinit var mediaContentRepositoryImpl: MediaContentRepositoryImpl

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WatchmodeApi::class.java)

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()

        castMemberDao = database.castMemberDao()
        crewMemberDao = database.crewMemberDao()
        favoriteMediaContentDao = database.favoriteMediaContentDao()
        streamingSourceDao = database.streamingSourceDao()
        streamingSourceLogoUrlDao = database.streamingSourceLogoUrlDao()

        mediaContentRepositoryImpl = MediaContentRepositoryImpl(
            api = api,
            castMemberDao = castMemberDao,
            crewMemberDao = crewMemberDao,
            favoriteMediaContentDao = favoriteMediaContentDao,
            streamingSourceDao = streamingSourceDao,
            streamingSourceLogoUrlDao = streamingSourceLogoUrlDao
        )
    }

    @Test
    fun saveStreamingSourceLogosUrl() {
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(
                "[{\"id\":203,\"name\":\"\",\"type\":\"\"," +
                        "\"logo_100px\":\"https://cdn.watchmode.com/provider_logos/netflix_100px.png\"," +
                        "\"ios_appstore_url\":\"\",\"android_playstore_url\":\"\"," +
                        "\"android_scheme\":\"\",\"ios_scheme\":\"\",\"regions\":[]}]\n"
            )
        mockWebServer.enqueue(response)

        runBlocking {
            mediaContentRepositoryImpl.saveStreamingSourceLogosUrl()

            val expected = "https://cdn.watchmode.com/provider_logos/netflix_100px.png"
            val actual = streamingSourceLogoUrlDao.getStreamingSourceLogoUrlById(203)

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun searchByTitle() {
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(
                "{\"results\":[{\"name\":\"Jurassic World Dominion\",\"relevance\":249.75," +
                        "\"type\":\"movie\",\"id\":1195869,\"year\":2022,\"result_type\":\"title\"," +
                        "\"tmdb_id\":507086,\"tmdb_type\":\"movie\",\"image_url\":" +
                        "\"https://cdn.watchmode.com/posters/01195869_poster_w185.jpg\"}]}\n"
            )
        mockWebServer.enqueue(response)

        runBlocking {
            val expected = listOf(
                MovieOverview(
                    id = 1195869,
                    title = "Jurassic World Dominion",
                    year = 2022,
                    imageUrl = "https://cdn.watchmode.com/posters/01195869_poster_w185.jpg"
                )
            )
            val actual = mediaContentRepositoryImpl.searchByTitle("Jurassic")
                .getOrDefault(emptyList())

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun getMediaContentDetails() {
        val castCrewResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[]")
        val sourcesResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[]")
        val detailsResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "{\"id\":1195869,\"title\":\"Jurassic World Dominion\",\"original_title\":" +
                        "\"Jurassic World Dominion\",\"plot_overview\":\"\",\"type\":\"movie\"" +
                        ",\"runtime_minutes\":147,\"year\":2022,\"end_year\":null,\"release_date\"" +
                        ":\"2022-06-01\",\"imdb_id\":\"tt8041270\",\"tmdb_id\":507086,\"tmdb_type\"" +
                        ":\"movie\",\"genres\":[],\"genre_names\":[],\"user_rating\":5.8,\"critic_score" +
                        "\":33,\"us_rating\":\"PG-13\",\"poster\":\"https://cdn.watchmode.com/posters/01195869_poster_w185.jpg\"" +
                        ",\"backdrop\":\"https://cdn.watchmode.com/backdrops/01195869_bd_w780.jpg\",\"original_language\":" +
                        "\"en\",\"similar_titles\":[],\"networks\":null,\"network_names\":[],\"relevance_percentile\":99.9,\"trailer" +
                        "\":\"https://www.youtube.com/watch?v=qVUNFBm1hYs\",\"trailer_thumbnail\":\"" +
                        "https://cdn.watchmode.com/video_thumbnails/764701_pthumbnail_320.jpg\"}\n"
            )
        mockWebServer.enqueue(castCrewResponse)
        mockWebServer.enqueue(sourcesResponse)
        mockWebServer.enqueue(detailsResponse)

        runBlocking {
            val expected = MovieDetails(
                id = 1195869,
                title = "Jurassic World Dominion",
                year = 2022,
                imageUrl = "https://cdn.watchmode.com/posters/01195869_poster_w185.jpg",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
            val actual = mediaContentRepositoryImpl.getMediaContentDetails(1195869)
                .getOrNull()

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun insertFavoriteMediaContent() = runBlocking {
        mediaContentRepositoryImpl.insertFavoriteMediaContent(
            MovieDetails(
                id = 1,
                title = "Test 1",
                year = 2022,
                imageUrl = "",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )

        val expected = MovieDetails(
            id = 1,
            title = "Test 1",
            year = 2022,
            imageUrl = "",
            description = "",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val actual = mediaContentRepositoryImpl.getFavoriteMediaContentDetails(mediaContentId = 1)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getFavoriteMediaContentDetails(): Unit = runBlocking {
        mediaContentRepositoryImpl.insertFavoriteMediaContent(
            MovieDetails(
                id = 2,
                title = "Test 2",
                year = 2022,
                imageUrl = "",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )

        val expected = MovieDetails(
            id = 2,
            title = "Test 2",
            year = 2022,
            imageUrl = "",
            description = "",
            streamingSources = emptyList(),
            crew = emptyList(),
            cast = emptyList()
        )
        val actual = mediaContentRepositoryImpl.getFavoriteMediaContentDetails(mediaContentId = 2)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getAllFavoriteMediaContentOverview() = runBlocking {
        if (database.isOpen) {
            database.close()
            database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                Database::class.java
            ).allowMainThreadQueries().build()
        }

        mediaContentRepositoryImpl.insertFavoriteMediaContent(
            MovieDetails(
                id = 1,
                title = "Test 1",
                year = 2022,
                imageUrl = "",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )

        val expected = listOf(MovieOverview(1, "Test 1", 2022, ""))
        val actual = mediaContentRepositoryImpl.getAllFavoriteMediaContentOverview()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deleteFavoriteMediaContent() = runBlocking {
        if (database.isOpen) {
            database.close()
            database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                Database::class.java
            ).allowMainThreadQueries().build()
        }

        mediaContentRepositoryImpl.insertFavoriteMediaContent(
            MovieDetails(
                id = 1,
                title = "Test 1",
                year = 2022,
                imageUrl = "",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )
        mediaContentRepositoryImpl.insertFavoriteMediaContent(
            MovieDetails(
                id = 2,
                title = "Test 2",
                year = 2022,
                imageUrl = "",
                description = "",
                streamingSources = emptyList(),
                crew = emptyList(),
                cast = emptyList()
            )
        )

        mediaContentRepositoryImpl.deleteFavoriteMediaContent(mediaContentId = 2)

        val expected = listOf(MovieOverview(1, "Test 1", 2022, ""))
        val actual = mediaContentRepositoryImpl.getAllFavoriteMediaContentOverview()

        Assert.assertEquals(expected, actual)
    }

    @After
    fun tearDown() {
        database.close()
        mockWebServer.shutdown()
    }
}