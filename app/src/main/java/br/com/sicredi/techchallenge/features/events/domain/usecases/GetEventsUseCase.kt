package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventsException
import kotlinx.coroutines.flow.Flow

interface IGetEventsUseCase {
    val eventsRepository: IEventsRepository

    @Throws(
        InvalidEventsException::class,
        EventsConversionException::class
    )
    suspend fun execute(): Flow<Events>
}

class GetEventsUseCase(
    override val eventsRepository: IEventsRepository
) : IGetEventsUseCase {

    @Throws(
        InvalidEventsException::class,
        EventsConversionException::class
    )
    override suspend fun execute(): Flow<Events> {
        return eventsRepository.getEvents()
    }
}