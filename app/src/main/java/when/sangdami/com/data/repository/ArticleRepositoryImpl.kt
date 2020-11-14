package `when`.sangdami.com.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext
import `when`.sangdami.com.data.datasource.ArticleDataSource
import `when`.sangdami.com.domain.ArticleRepository
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.Result
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val flowerSearchRemoteDataSource: ArticleDataSource
) : ArticleRepository {

    private val articleList = MutableLiveData<List<AbridgeArticle>>()

    override fun observeArticleList(): Flow<List<AbridgeArticle>> {
        return articleList.asFlow().filterNotNull()
    }

    override suspend fun updateArticles(): Result<Unit> = withContext(Dispatchers.IO) {

        return@withContext when (val articleResult = flowerSearchRemoteDataSource.getArticleList()) {
            is Result.Success -> {
                Result.Success(Unit).apply{
                    articleList.postValue(articleResult.value)
                }
            }
            is Result.Failure -> {
                Result.Failure<Unit>(articleResult.throwable)
            }
        }
    }

    override suspend fun getArticle(id: Int): Result<Article> = withContext(Dispatchers.IO) {
        flowerSearchRemoteDataSource.getArticle(id)
    }
}