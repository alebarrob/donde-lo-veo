package barrera.alejandro.dondeloveo.data.enums

import org.junit.Test
import org.junit.Assert.assertEquals

class TeamMemberTypeTest {

    @Test
    fun `test fromValue given 'Crew' returns CREW`() {
        val expected = TeamMemberType.CREW
        val actual = TeamMemberType.fromValue("Crew")
        assertEquals(expected, actual)
    }

    @Test
    fun `test fromValue given 'Cast' returns CAST`() {
        val expected = TeamMemberType.CAST
        val actual = TeamMemberType.fromValue("Cast")
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test fromValue given unknown value throws IllegalArgumentException`() {
        TeamMemberType.fromValue("unknown")
    }
}