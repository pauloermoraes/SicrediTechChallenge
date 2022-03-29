package br.com.sicredi.techchallenge.features.events.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.domain.usecases.IEventCheckInUseCase
import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EventCheckInViewModel(
    private val iEventCheckInUseCase: IEventCheckInUseCase,
    private val eventId: String,
) : SicrediViewModel() {

    private val _eventCheckInLiveData = MutableLiveData<Boolean>()
    val eventCheckInLiveData: LiveData<Boolean> = _eventCheckInLiveData

    fun eventCheckIn(name: String, email: String) {
        coroutineScope.launch {
            iEventCheckInUseCase.execute(
                EventCheckIn(
                    eventId,
                    name,
                    email
                )
            ).onStart {
                onLoading()
            }.catch { _throwable ->
                handleEventCheckIn(throwable = _throwable)
            }.collect { _checkedIn ->
                handleEventCheckIn(checkedIn = _checkedIn)
            }
        }
    }

    private fun handleEventCheckIn(checkedIn: Boolean? = null, throwable: Throwable? = null) {
        this@EventCheckInViewModel._errorLiveData.postValue(throwable)
        checkedIn?.let { _checkedIn ->
            this@EventCheckInViewModel._eventCheckInLiveData.postValue(_checkedIn)
        }
        onNotLoading()
    }

}