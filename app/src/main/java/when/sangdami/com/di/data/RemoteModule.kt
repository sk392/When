package net.daum.android.daum.di.data

import dagger.Module
import dagger.Provides
import `when`.sangdami.com.data.server.ArticleServer
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideArticleService(): ArticleServer.ArticleService = ArticleServer.articleService()
}