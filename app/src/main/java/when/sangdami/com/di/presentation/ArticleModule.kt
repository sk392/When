package `when`.sangdami.com.di.presentation

import dagger.Module
import dagger.Subcomponent
import dagger.android.ContributesAndroidInjector
import `when`.sangdami.com.presentation.ArticleFragment
import `when`.sangdami.com.presentation.viewmodel.ArticleViewModel

@Module(subcomponents = [ArticleViewModelSubComponent::class])
abstract class ArticleModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun articleFragment(): ArticleFragment
}


@Subcomponent
interface ArticleViewModelSubComponent {

    fun articleViewModel(): ArticleViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticleViewModelSubComponent
    }
}
