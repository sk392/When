package `when`.sangdami.com

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import `when`.sangdami.com.di.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(this, this).inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}