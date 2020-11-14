package `when`.sangdami.com.util

import android.os.Looper
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import java.lang.ref.WeakReference


abstract class LiveEvent<T>(
    protected val source: MutableLiveData<T>
) {

    private val mediators: MutableList<WeakReference<LiveData<T>>> = mutableListOf()

    private fun createMediator(): MediatorLiveData<T> {
        return MediatorLiveData<T>().also { result ->
            var skip =  source.value != null
            val fakeObserver = Observer<T> {  }
            if (skip) {
                result.observeForever(fakeObserver)
            }
            result.addSource(source) {
                when {
                    skip -> {
                        skip = false
                        result.removeObserver(fakeObserver)
                    }
                    it != null -> result.value = it
                }
            }
        }.apply {
            mediators.add(WeakReference(this))
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        removeNullMediators()
        createMediator().observe(owner, observer)
    }

    fun asFlow(): Flow<T> {
        removeNullMediators()
        return createMediator().asFlow()
    }

    fun removeObservers(owner: LifecycleOwner) {
        removeNullMediators()
        mediators.forEach { it.get()?.removeObservers(owner) }
    }

    fun removeObserver(observer: Observer<T>) {
        removeNullMediators()
        mediators.forEach { it.get()?.removeObserver(observer) }
    }

    private fun removeNullMediators() {
        mediators.removeAll { it.get() == null }
    }

}

class MutableLiveEvent<T>(
    source: MutableLiveData<T> = MutableLiveData()
) : LiveEvent<T>(source) {

    fun send(value: T) {
        if (Thread.currentThread() == Looper.getMainLooper().thread) {
            source.value = value
        } else {
            source.postValue(value)
        }
    }

}

