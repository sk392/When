package `when`.sangdami.com.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import `when`.sangdami.com.domain.entity.AbridgeArticle
import `when`.sangdami.com.domain.usecase.ObserveArticlesUseCase
import `when`.sangdami.com.domain.usecase.UpdateArticlesUseCase
import javax.inject.Inject


class HomeSubViewModel @Inject constructor(
    private val observeArticlesUseCase: ObserveArticlesUseCase,
    private val updateArticlesUseCase: UpdateArticlesUseCase
): ViewModel(){

    private val _articles = MutableLiveData<List<AbridgeArticle>>()
    val articles : LiveData<List<AbridgeArticle>> get() = _articles

    init {
        observeArticles()
    }

    private fun observeArticles(){
        viewModelScope.launch {
            observeArticlesUseCase().collect {
                _articles.value = it
            }
        }
    }

    fun updateArticles(){
        viewModelScope.launch {
            updateArticlesUseCase()
        }
    }



}