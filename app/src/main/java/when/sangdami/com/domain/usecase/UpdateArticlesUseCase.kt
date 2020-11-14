package `when`.sangdami.com.domain.usecase

import `when`.sangdami.com.domain.ArticleRepository
import `when`.sangdami.com.domain.entity.Result
import javax.inject.Inject

class UpdateArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return articleRepository.updateArticles()
    }
}