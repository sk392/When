package `when`.sangdami.com.domain.usecase

import `when`.sangdami.com.domain.ArticleRepository
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.Result
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(id: Int): Result<Article> {
        return articleRepository.getArticle(id)
    }
}