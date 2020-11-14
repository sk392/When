package `when`.sangdami.com.di.presentation

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import `when`.sangdami.com.presentation.HomeFragment
import `when`.sangdami.com.presentation.viewmodel.HomeSubViewModel
import `when`.sangdami.com.presentation.viewmodel.HomeViewModel

@Module(subcomponents = [HomeViewModelSubComponent::class])
abstract class HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragment() : HomeFragment
}


@Subcomponent
interface HomeViewModelSubComponent {

    fun homeSubViewModel(): HomeSubViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeViewModelSubComponent
    }
}
