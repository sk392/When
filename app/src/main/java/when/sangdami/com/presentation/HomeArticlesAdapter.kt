package `when`.sangdami.com.presentation

import `when`.sangdami.com.R
import `when`.sangdami.com.domain.entity.AbridgeArticle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeArticlesAdapter(
    private val onClickListener: (articleId: Int?) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {
    private var items = mutableListOf<AbridgeArticle>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.article_item, parent, false)
        ) {
            onClickListener(it)
        }
    }

    override fun getItemCount() = items.count()

    fun setArticles(list: List<AbridgeArticle>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.onBind(items[position])
    }


}

class ArticleViewHolder(itemView: View, private val onClickListener: (id: Int?) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private var item: AbridgeArticle? = null
    private var title = itemView.findViewById<TextView>(R.id.title)
    private var background = itemView.findViewById<ImageView>(R.id.background)

    fun onBind(item: AbridgeArticle) {
        this.item = item

        itemView.setOnClickListener { onClickListener(item.id) }
        title.text = item.title
        Glide.with(itemView.context)
            .load(item.coverUrl)
            .centerCrop()
            .into(background)
    }

}