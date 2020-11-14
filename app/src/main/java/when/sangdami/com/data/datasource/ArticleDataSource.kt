package `when`.sangdami.com.data.datasource

import android.content.Context
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import `when`.sangdami.com.data.dto.ApiResult
import `when`.sangdami.com.data.server.ArticleServer
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.Result
import `when`.sangdami.com.util.MoshiUtils
import java.io.InputStream
import javax.inject.Inject

class ArticleDataSource @Inject constructor(
    private val service: ArticleServer.ArticleService,
    private val context: Context
) {
    companion object {
        private const val ASSETS_ARTICLE = "article.json"
        private const val ASSETS_ARTICLE_LIST = "articles.json"
    }

    private val isTesting = false

    suspend fun getArticleList(): Result<List<AbridgeArticle>> {
        return withContext(Dispatchers.IO) {
            val result = if (isTesting) {
                val inputStream: InputStream = context.assets.open(ASSETS_ARTICLE_LIST)
                val str = inputStream.bufferedReader().use { it.readText() }
                MoshiUtils.fromJson<ApiResult<List<AbridgeArticle>>?>(
                    str,
                    Types.newParameterizedType(
                        ApiResult::class.java,
                        Types.newParameterizedType(List::class.java, AbridgeArticle::class.java)
                    )
                )
            } else {
                service.getArticles()
            }

            if (result?.status == "200" && result.result != null) {
                Result.Success(result.result)
            } else {
                Result.Failure<List<AbridgeArticle>>(Exception(result?.exception))
            }
        }
    }

    suspend fun getArticle(id: Int): Result<Article> {
        return withContext(Dispatchers.IO) {
            val result = if (isTesting) {
                val inputStream: InputStream = context.assets.open(ASSETS_ARTICLE)
                val str = inputStream.bufferedReader().use { it.readText() }
                MoshiUtils.fromJson<ApiResult<Article>>(
                    str,
                    Types.newParameterizedType(ApiResult::class.java, Article::class.java)
                )

            } else {
                service.getArticle(id)
            }

            val article = result?.result
            if (result?.status == "200" && article != null) {
                Result.Success(article)
            } else {
                Result.Failure<Article>(Exception(result?.exception))
            }
        }
    }
}