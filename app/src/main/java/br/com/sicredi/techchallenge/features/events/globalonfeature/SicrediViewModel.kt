package br.com.sicredi.techchallenge.features.events.globalonfeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.util.concurrent.atomic.AtomicInteger

abstract class SicrediViewModel : ViewModel() {
    protected val coroutineScope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    private val loadingItemsCount = AtomicInteger(0)

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    protected val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> = _errorLiveData


    protected fun onLoading() {
        if (loadingItemsCount.incrementAndGet() > 0) {
            this@SicrediViewModel._loadingLiveData.postValue(true)
        }
    }

    protected fun onNotLoading() {
        if (loadingItemsCount.decrementAndGet() <= 0) {
            this@SicrediViewModel._loadingLiveData.postValue(false)
            loadingItemsCount.set(0)
        }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}