package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface IGetEventDetailsUseCase {
    val eventDetailsRepository: IEventsRepository

    @Throws(InvalidEventIdException::class)
    suspend fun execute(eventId: String): Flow<Event>
}

class GetEventDetailsUseCase(
    override val eventDetailsRepository: IEventsRepository
) : IGetEventDetailsUseCase {

    @Throws(InvalidEventIdException::class)
    override suspend fun execute(eventId: String): Flow<Event> {
        return flow {
            if (eventId.isEmpty()) throw InvalidEventIdException()
            emit(eventDetailsRepository.getEventDetails(eventId).first())
        }
    }
}