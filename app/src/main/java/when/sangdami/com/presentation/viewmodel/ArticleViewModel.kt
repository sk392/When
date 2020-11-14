package `when`.sangdami.com.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import `when`.sangdami.com.domain.entity.Article
import `when`.sangdami.com.domain.entity.Result
import `when`.sangdami.com.domain.usecase.GetArticleUseCase
import `when`.sangdami.com.util.LiveEvent
import `when`.sangdami.com.util.MutableLiveEvent
import android.util.Log
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase
):ViewModel(){

    private val _article = MutableLiveData<Article>()
    val article : LiveData<Article> get() = _article

    private val _showError = MutableLiveEvent<String?>()
    val showError : LiveEvent<String?> get() = _showError

    fun getArticle(id: Int?) {
        id ?: return
        viewModelScope.launch {
            when(val result = getArticleUseCase(id)){
                is Result.Success -> {
                    _article.value = result.value
                }
                is Result.Failure -> {
                    _showError.send(result.throwable.message)
                }
            }
        }
    }
}