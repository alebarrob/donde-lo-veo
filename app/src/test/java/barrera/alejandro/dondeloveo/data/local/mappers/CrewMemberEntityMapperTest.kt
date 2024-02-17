package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.CrewMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toCrewMember
import barrera.alejandro.dondeloveo.data.local.mappers.toCrewMemberEntity
import barrera.alejandro.dondeloveo.domain.model.CrewMember
import org.junit.Assert.assertEquals
import org.junit.Test

class CrewMemberEntityMapperTest {
    @Test
    fun `test CrewMemberEntity to CrewMember mapping`() {
        val crewMemberEntity = CrewMemberEntity(
            id = 1,
            mediaContentId = 2,
            name = "Name",
            imageUrl = "imageUrl",
            role = "Role"
        )
        val crewMember = crewMemberEntity.toCrewMember()

        assertEquals(2, crewMember.id)
        assertEquals("Name", crewMember.name)
        assertEquals("imageUrl", crewMember.imageUrl)
        assertEquals("Role", crewMember.role)
    }

    @Test
    fun `test CrewMember to CrewMemberEntity mapping`() {
        val crewMember = CrewMember(
            id = 2,
            name = "Name",
            imageUrl = "imageUrl",
            role = "Role"
        )
        val crewMemberEntity = crewMember.toCrewMemberEntity(mediaContentId = 1)

        assertEquals(0, crewMemberEntity.id)
        assertEquals(1, crewMemberEntity.mediaContentId)
        assertEquals("Name", crewMemberEntity.name)
        assertEquals("imageUrl", crewMemberEntity.imageUrl)
        assertEquals("Role", crewMemberEntity.role)
    }
}