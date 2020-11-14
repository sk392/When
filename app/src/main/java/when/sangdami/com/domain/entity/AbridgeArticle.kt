package `when`.sangdami.com.domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbridgeArticle(
    val id : Int?,
    val title : String?,
    val authorName : String?,
    val authorProfileUrl : String?,
    val modifyDate : String?,
    val coverUrl : String?
)