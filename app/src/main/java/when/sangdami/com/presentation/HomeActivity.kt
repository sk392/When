package `when`.sangdami.com.presentation

import `when`.sangdami.com.R
import `when`.sangdami.com.databinding.ActivityHomeBinding
import `when`.sangdami.com.presentation.viewmodel.HomeViewModel
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasAndroidInjector,
    HomeNavigator {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val runnable = Runnable {
            Toast.makeText(this,"asdasdasdasd" ,Toast.LENGTH_SHORT).show()
        }
        val handler = Handler()
        handler.postDelayed(runnable,6000)
        handler.removeCallbacks(runnable)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        addHomeFragment()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun showArticle(articleId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, ArticleFragment.newInstance(articleId), ArticleFragment.TAG)
            .addToBackStack(ArticleFragment.TAG)
            .commit()
    }

    private fun addHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container,
                HomeFragment.newInstance(),
                HomeFragment.TAG
            )
            .commit()
    }

}