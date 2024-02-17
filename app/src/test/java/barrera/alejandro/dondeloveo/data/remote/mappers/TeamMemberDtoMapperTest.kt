package barrera.alejandro.dondeloveo.data.remote.mappers

import barrera.alejandro.dondeloveo.data.remote.dto.TeamMemberDto
import org.junit.Test
import org.junit.Assert.assertEquals

class TeamMemberDtoMapperTest {
    @Test
    fun `test TeamMemberDto to TeamMember mapper for crew`() {
        val teamMemberDto = TeamMemberDto(
            personId = 1,
            type = "Crew",
            fullName = "fullName",
            headshotUrl = "imageUrl",
            role = "role"
        )
        val teamMember = teamMemberDto.toTeamMember()

        assertEquals(1, teamMember.id)
        assertEquals("fullName", teamMember.name)
        assertEquals("imageUrl", teamMember.imageUrl)
        assertEquals("role", teamMember.role)
    }

    @Test
    fun `test TeamMemberDto to TeamMember mapper for cast`() {
        val teamMemberDto = TeamMemberDto(
            personId = 1,
            type = "Cast",
            fullName = "fullName",
            headshotUrl = "imageUrl",
            role = "role"
        )
        val teamMember = teamMemberDto.toTeamMember()

        assertEquals(1, teamMember.id)
        assertEquals("fullName", teamMember.name)
        assertEquals("imageUrl", teamMember.imageUrl)
        assertEquals("role", teamMember.role)
    }
}