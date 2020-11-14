package `when`.sangdami.com.domain

import kotlinx.coroutines.flow.Flow
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.Result

interface ArticleRepository {
    suspend fun updateArticles() : Result<Unit>

    suspend fun getArticle(id : Int) : Result<Article>

    fun observeArticleList() : Flow<List<AbridgeArticle>>
}