package `when`.sangdami.com.presentation

import `when`.sangdami.com.R
import `when`.sangdami.com.databinding.FragmentArticleBinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.android.support.AndroidSupportInjection
import `when`.sangdami.com.di.presentation.ArticleViewModelSubComponent
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.ArticleType
import `when`.sangdami.com.presentation.model.ArticlePage
import `when`.sangdami.com.presentation.viewmodel.ArticleViewModel
import `when`.sangdami.com.util.assistedViewModel
import javax.inject.Inject

class ArticleFragment : Fragment() ,SwipeDelegate{
    companion object {
        fun newInstance(articleId: Int): ArticleFragment {
            return ArticleFragment().apply {
                arguments = Bundle().apply { putInt(ARTICLE_ID, articleId) }
            }
        }

        const val TAG = "ArticleFragment"
        private const val ARTICLE_ID = "articleId"
    }

    private lateinit var binding: FragmentArticleBinding

    @Inject
    lateinit var viewModelFactory: ArticleViewModelSubComponent.Factory
    private val viewModel: ArticleViewModel by assistedViewModel {
        viewModelFactory.create().articleViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.article.observe(viewLifecycleOwner, Observer {
            when(it.type){
                ArticleType.SWIPE -> {
                    attachSwipeArticle(it)
                }
                ArticleType.NORMAL -> {
                    attachNormalArticle(it)
                }
            }
        })
        viewModel.getArticle(arguments?.getInt(ARTICLE_ID))
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun attachSwipeArticle(article: Article){
        binding.swipeArticle.visibility = View.VISIBLE
        binding.normalArticle.visibility = View.GONE

        binding.swipeArticle.adapter = PagerAdapter(article,this)
        binding.swipeArticle.adapter?.notifyDataSetChanged()
    }

    private fun attachNormalArticle(article: Article){
        binding.swipeArticle.visibility = View.GONE
        binding.normalArticle.visibility = View.VISIBLE
        childFragmentManager.beginTransaction()
            .replace(R.id.normal_article, NormalArticleFragment.newInstance(article), NormalArticleFragment.TAG)
            .commit()

    }

    private inner class PagerAdapter(private val article : Article, f: Fragment) : FragmentStateAdapter(f) {
        private var contents = article.contents?.filterNotNull() ?: listOf()

        override fun getItemCount(): Int = contents.size + 2//title + profile

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    ArticlePageFragment.newInstance(
                        ArticlePage.TitlePage(
                            article.title ?: "",
                            article.coverUrl ?: ""
                        )
                    )
                }
                itemCount -1 -> {
                    ArticlePageFragment.newInstance(
                        ArticlePage.ProfilePage(
                            0,
                            article.authorName ?: "",
                            article.authorProfileUrl ?: ""
                        )
                    )
                }
                else -> {
                    ArticlePageFragment.newInstance(
                        ArticlePage.ContentsPage(
                            contents[position - 1],
                            "$position/${itemCount-2}"
                        )
                    )
                }
            }
        }
    }

    override fun swipeLeft() {
        binding.swipeArticle.currentItem = binding.swipeArticle.currentItem -1
    }

    override fun swipeRight() {
        binding.swipeArticle.currentItem = binding.swipeArticle.currentItem +1
    }
}

interface SwipeDelegate{
    fun swipeLeft()
    fun swipeRight()
}