package br.com.sicredi.techchallenge.features.events.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.domain.usecases.IGetEventsUseCase
import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EventsViewModel(
    private val iGetEventsUseCase: IGetEventsUseCase
) : SicrediViewModel() {

    private val _eventsLiveData = MutableLiveData<Events>()
    val eventsLiveData: LiveData<Events> = _eventsLiveData

    fun getEvents() {
        coroutineScope.launch {
            iGetEventsUseCase.execute()
                .onStart {
                    onLoading()
                }
                .catch { _throwable ->
                    handleEvents(throwable = _throwable)
                    onNotLoading()
                }
                .collect { _events ->
                    handleEvents(events = _events)
                    onNotLoading()
                }
        }
    }

    private fun handleEvents(events: Events? = null, throwable: Throwable? = null) {
        this@EventsViewModel._errorLiveData.postValue(throwable)
        events?.let { _events ->
            this@EventsViewModel._eventsLiveData.postValue(_events)
        }
    }
}