package barrera.alejandro.dondeloveo.data.local.mappers

import barrera.alejandro.dondeloveo.data.local.entity.CastMemberEntity
import barrera.alejandro.dondeloveo.data.local.mappers.toCastMember
import barrera.alejandro.dondeloveo.data.local.mappers.toCastMemberEntity
import barrera.alejandro.dondeloveo.domain.model.CastMember
import org.junit.Test
import org.junit.Assert.assertEquals

class CastMemberEntityMapperTest {
    @Test
    fun `test CastMemberEntity to CastMember mapping`() {
        val castMemberEntity = CastMemberEntity(
            id = 1,
            mediaContentId = 2,
            name = "Name",
            imageUrl = "imageUrl",
            role = "Role"
        )
        val castMember = castMemberEntity.toCastMember()

        assertEquals(2, castMember.id)
        assertEquals("Name", castMember.name)
        assertEquals("imageUrl", castMember.imageUrl)
        assertEquals("Role", castMember.role)
    }

    @Test
    fun `test CastMember to CastMemberEntity mapping`() {
        val castMember = CastMember(
            id = 2,
            name = "Name",
            imageUrl = "imageUrl",
            role = "Role"
        )
        val castMemberEntity = castMember.toCastMemberEntity(mediaContentId = 1)

        assertEquals(0, castMemberEntity.id)
        assertEquals(1, castMemberEntity.mediaContentId)
        assertEquals("Name", castMemberEntity.name)
        assertEquals("imageUrl", castMemberEntity.imageUrl)
        assertEquals("Role", castMemberEntity.role)
    }
}