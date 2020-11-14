package `when`.sangdami.com.presentation

import `when`.sangdami.com.R
import `when`.sangdami.com.databinding.FragmentHomeBinding
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import `when`.sangdami.com.di.presentation.HomeViewModelSubComponent
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.presentation.viewmodel.HomeSubViewModel
import `when`.sangdami.com.util.assistedViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }

        const val TAG = "HomeFragment"
    }

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelFactory: HomeViewModelSubComponent.Factory
    private val viewModel: HomeSubViewModel by assistedViewModel {
        viewModelFactory.create().homeSubViewModel()
    }

    private var articlesAdapter: HomeArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            onLoadedAricles(it)
        })

        initRecyclerView()
        loadArticles()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun initRecyclerView() {
        articlesAdapter =
            HomeArticlesAdapter { articleId ->
                if (activity is HomeNavigator && articleId != null) {
                    (activity as HomeNavigator).showArticle(articleId)
                }
            }
        binding.articles.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//            addItemDecoration(
//                OffsetDecoration(
//                    resources.getDimensionPixelSize(R.dimen.home_articles_divider)
//                )
//            )
        }
    }

    private fun loadArticles() {
        binding.articles.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
        viewModel.updateArticles()
    }

    private fun onLoadedAricles(items: List<AbridgeArticle>) {
        binding.articles.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        articlesAdapter?.setArticles(items)
    }


    private class OffsetDecoration(val offsetHeight: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = 0
            outRect.bottom = offsetHeight

            val position = parent.getChildAdapterPosition(view)
            val adapter = parent.adapter ?: return
            if (position == adapter.itemCount - 1) {
                outRect.bottom = 0
            }
        }
    }
}
