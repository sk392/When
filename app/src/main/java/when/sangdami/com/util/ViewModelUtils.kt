package `when`.sangdami.com.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> Fragment.assistedViewModel(
    crossinline provider: () -> VM
): Lazy<VM> {
    return viewModels {
        @Suppress("UNCHECKED_CAST")
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel> create(modelClass: Class<VM>) = provider() as VM
        }
    }
}