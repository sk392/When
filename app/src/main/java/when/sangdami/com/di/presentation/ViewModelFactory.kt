package `when`.sangdami.com.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creatorMap: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return requireNotNull(creatorMap[modelClass], { "unknown model class $modelClass" }).get() as T
    }

}