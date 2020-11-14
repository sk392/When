package `when`.sangdami.com.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import `when`.sangdami.com.App
import `when`.sangdami.com.di.data.RepositoryModule
import `when`.sangdami.com.di.presentation.ActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    RepositoryModule::class
])

interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance application: Application
        ): AppComponent
    }
}