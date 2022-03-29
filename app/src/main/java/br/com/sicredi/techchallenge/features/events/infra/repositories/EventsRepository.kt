package br.com.sicredi.techchallenge.features.events.infra.repositories

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.features.events.infra.mappers.EventResponseToEventMapper
import br.com.sicredi.techchallenge.features.events.infra.mappers.EventsResponseToEventsMapper
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventDetailsException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class EventsRepository(
    override val iEventsApi: IEventsApi
) : IEventsRepository {

    @Throws(
        InvalidEventsException::class,
        EventsConversionException::class
    )
    override suspend fun getEvents(): Flow<Events> = flow {
        val events = iEventsApi.getEvents().first()
        if (events == null || events.isEmpty()) throw InvalidEventsException()
        emit(EventsResponseToEventsMapper().map(events))
    }

    @Throws(
        InvalidEventIdException::class,
        InvalidEventDetailsException::class
    )
    override suspend fun getEventDetails(eventId: String): Flow<Event> {
        return flow {
            if (eventId.isEmpty()) throw InvalidEventIdException()
            val eventDetails = iEventsApi.getEventDetails(eventId).first()
            if (eventDetails == null || eventDetails.id?.isNotEmpty() != true) {
                throw InvalidEventDetailsException()
            }

            emit(EventResponseToEventMapper().map(eventDetails))
        }
    }
}