package `when`.sangdami.com.domain.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Article(
    val articleId : Int?,
    val title : String?,
    val authorName : String?,
    val authorProfileUrl : String?,
    val coverUrl : String?,
    val contents : List<String?>?,
    val type : ArticleType?
) : Parcelable

enum class ArticleType {
    NORMAL, SWIPE
}