package `when`.sangdami.com.domain.entity

import `when`.sangdami.com.domain.entity.AbridgeArticle
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResult(
    val list : List<AbridgeArticle?>?
)