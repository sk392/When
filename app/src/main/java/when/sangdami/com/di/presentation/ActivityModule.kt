package `when`.sangdami.com.di.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import `when`.sangdami.com.presentation.HomeActivity
import net.daum.android.daum.di.presentation.ActivityScope

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        HomeModule::class,
        ArticleModule::class
    ])
    abstract fun homeActivity(): HomeActivity
}