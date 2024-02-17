package barrera.alejandro.dondeloveo.presentation.mappers

import barrera.alejandro.dondeloveo.domain.model.CastMember
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import barrera.alejandro.dondeloveo.presentation.model.UiCastMember
import barrera.alejandro.dondeloveo.presentation.model.UiCrewMember
import org.junit.Assert
import org.junit.Test

class UiTeamMemberMapperTest {
    @Test
    fun `test UiTeamMember to TeamMember mapper for cast`() {
        val uiCastMember = UiCastMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val expected = CastMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val actual = uiCastMember.toTeamMember()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test UiTeamMember to TeamMember mapper for crew`() {
        val uiCrewMember = UiCrewMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val expected = CrewMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val actual = uiCrewMember.toTeamMember()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test TeamMember to UiTeamMember mapper for cast`() {
        val castMember = CastMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val expected = UiCastMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val actual = castMember.toUiTeamMember()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test TeamMember to UiTeamMember mapper for crew`() {
        val crewMember = CrewMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val expected = UiCrewMember(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl",
            role = "role"
        )
        val actual = crewMember.toUiTeamMember()

        Assert.assertEquals(expected, actual)
    }
}