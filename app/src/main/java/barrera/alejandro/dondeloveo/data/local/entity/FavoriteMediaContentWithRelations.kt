package barrera.alejandro.dondeloveo.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteMediaContentWithRelations(
    @Embedded
    val favoriteMediaContent: FavoriteMediaContentEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "mediaContentId"
    )
    val streamingSources: List<StreamingSourceEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "mediaContentId"
    )
    val crew: List<CrewMemberEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "mediaContentId"
    )
    val cast: List<CastMemberEntity>
)
