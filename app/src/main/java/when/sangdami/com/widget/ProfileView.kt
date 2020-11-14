package `when`.sangdami.com.widget

import `when`.sangdami.com.R
import `when`.sangdami.com.domain.entity.Article
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class ProfileView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var name : TextView
    private lateinit var profileImage : ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_profile, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        name = findViewById(R.id.profile_name)
        profileImage = findViewById(R.id.profile_image)
    }

    fun setArticle(article: Article?){
        article?.apply{
            name.text = article.authorName
            Glide.with(this@ProfileView)
                .load(article.authorProfileUrl)
                .circleCrop()
                .into(profileImage)
        }
    }

}