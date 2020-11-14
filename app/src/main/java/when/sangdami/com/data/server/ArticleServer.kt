package `when`.sangdami.com.data.server

import `when`.sangdami.com.data.dto.ApiResult
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.domain.entity.Article
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

object ArticleServer {
    private const val ARTICLE_SERVER_BASE_URL = "https://silvernode.cafe24.com/when/"

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ARTICLE_SERVER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    fun articleService(): ArticleService = retrofit()
        .create(ArticleService::class.java)

    interface ArticleService {

        @Headers("Cache-control: no-cache")
        @GET("articles")
        suspend fun getArticles(): ApiResult<List<AbridgeArticle>>

        @GET("articles/{articleID}")
        suspend fun getArticle(
            @Path("articleID") id: Int
        ): ApiResult<Article>

    }
}