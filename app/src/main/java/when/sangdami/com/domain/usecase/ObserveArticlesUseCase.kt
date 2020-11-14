package `when`.sangdami.com.domain.usecase

import `when`.sangdami.com.domain.ArticleRepository
import kotlinx.coroutines.flow.Flow
import `when`.sangdami.com.domain.entity.AbridgeArticle
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator  fun invoke() : Flow<List<AbridgeArticle>> {
       return articleRepository.observeArticleList()
    }
}