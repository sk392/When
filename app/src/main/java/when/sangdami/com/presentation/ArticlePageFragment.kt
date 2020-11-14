package `when`.sangdami.com.presentation

import `when`.sangdami.com.databinding.FragmentArticlePageBinding
import `when`.sangdami.com.presentation.model.ArticlePage
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class ArticlePageFragment : Fragment() {
    companion object {
        private const val ARTICLE_PAGE = "articlePage"
        fun newInstance(page: ArticlePage) =
            ArticlePageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARTICLE_PAGE, page)
                }
            }
    }

    private lateinit var binding: FragmentArticlePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(val articlePage = arguments?.getParcelable<ArticlePage>(ARTICLE_PAGE)){
            is ArticlePage.TitlePage -> {
                Glide.with(requireContext())
                    .load(articlePage.coverUrl)
                    .centerCrop()
                    .into(binding.container)
                binding.title.text = articlePage.title
                binding.title.visibility = View.VISIBLE
                binding.content.visibility = View.GONE
            }

            is ArticlePage.ContentsPage -> {
                binding.content.text = articlePage.content
                binding.index.visibility = View.VISIBLE
                binding.index.text = articlePage.position
            }

            is ArticlePage.ProfilePage -> {
                binding.profile.visibility = View.VISIBLE
                binding.profileName.text = articlePage.name
                Glide.with(this)
                    .load(articlePage.profileUrl)
                    .circleCrop()
                    .into(binding.profileImage)
            }
        }

        binding.swipeLeft.setOnClickListener {
            //TODO 예쁘게 바꾸는거 고민해보기.ㅋㅋ루삥뽕
            if(parentFragment is SwipeDelegate){
                (parentFragment as SwipeDelegate).swipeLeft()
            }
        }
        binding.swipeRight.setOnClickListener {
            if(parentFragment is SwipeDelegate){
                (parentFragment as SwipeDelegate).swipeRight()
            }
        }
    }

}