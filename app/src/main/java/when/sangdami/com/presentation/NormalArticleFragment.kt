package `when`.sangdami.com.presentation

import `when`.sangdami.com.databinding.FragmentNormalArticleBinding
import `when`.sangdami.com.domain.entity.Article
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.lang.StringBuilder


class NormalArticleFragment : Fragment() {
    companion object {

        const val TAG = "NormalArticleFragment"
        private const val ARTICLE_NAME = "articleName"

        fun newInstance(article: Article) =
            NormalArticleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARTICLE_NAME, article)
                }
            }
    }

    private lateinit var binding: FragmentNormalArticleBinding
    private var article: Article?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article =  arguments?.getParcelable(ARTICLE_NAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNormalArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        Glide.with(this)
            .load(article?.coverUrl)
            .centerCrop()
            .into(binding.titleBackground)

        binding.title.text = article?.title
        binding.content.text = article?.run {
            val result = StringBuilder()
            article?.contents?.forEach {
                result.append(it)
                Log.e("lattelog", "initialize: $it")
                result.append("\n\n")
            }

            result.toString()
        }
        binding.profile.setArticle(article)
    }

}