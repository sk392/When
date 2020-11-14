package `when`.sangdami.com.di.presentation

import `when`.sangdami.com.di.presentation.ViewModelFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}