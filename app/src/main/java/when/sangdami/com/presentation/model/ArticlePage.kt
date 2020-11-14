package `when`.sangdami.com.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class ArticlePage : Parcelable{

    @Parcelize
    data class ContentsPage(
        val content : String,
        val position : String
    ) : ArticlePage()

    @Parcelize
    data class ProfilePage(
        val userId: Int,
        val name : String,
        val profileUrl : String
    ) : ArticlePage()

    @Parcelize
    data class TitlePage(
        val title : String,
        val coverUrl : String
    ) : ArticlePage()
}