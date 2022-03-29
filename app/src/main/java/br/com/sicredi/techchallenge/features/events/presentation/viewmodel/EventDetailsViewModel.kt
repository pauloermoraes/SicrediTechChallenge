package br.com.sicredi.techchallenge.features.events.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.usecases.IGetEventDetailsUseCase
import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val iGetEventDetailsUseCase: IGetEventDetailsUseCase,
    private val eventId: String
) : SicrediViewModel() {

    private val _eventDetailsLiveData = MutableLiveData<Event>()
    val eventDetailsLiveData: LiveData<Event> = _eventDetailsLiveData

    fun getEventDetails() {
        coroutineScope.launch {
            iGetEventDetailsUseCase.execute(eventId)
                .onStart {
                    onLoading()
                }
                .catch { _throwable ->
                    handleEventDetails(throwable = _throwable)
                }
                .collect { _event ->
                    handleEventDetails(event = _event)
                }
        }
    }

    private fun handleEventDetails(event: Event? = null, throwable: Throwable? = null) {
        this@EventDetailsViewModel._errorLiveData.postValue(throwable)
        event?.let { _event ->
            this@EventDetailsViewModel._eventDetailsLiveData.postValue(_event)
        }
        onNotLoading()
    }
}