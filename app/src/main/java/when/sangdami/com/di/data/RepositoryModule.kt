package `when`.sangdami.com.di.data

import dagger.Binds
import dagger.Module
import `when`.sangdami.com.data.repository.ArticleRepositoryImpl
import `when`.sangdami.com.domain.ArticleRepository
import net.daum.android.daum.di.data.RemoteModule
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindZzimRepository(zzimRepositoryImpl: ArticleRepositoryImpl): ArticleRepository

}