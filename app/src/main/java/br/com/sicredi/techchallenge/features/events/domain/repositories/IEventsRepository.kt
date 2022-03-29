package br.com.sicredi.techchallenge.features.events.domain.repositories

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventDetailsException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventsException
import kotlinx.coroutines.flow.Flow

interface IEventsRepository {

    val iEventsApi: IEventsApi

    @Throws(
        InvalidEventsException::class,
        EventsConversionException::class
    )
    suspend fun getEvents(): Flow<Events>

    @Throws(
        InvalidEventIdException::class,
        InvalidEventDetailsException::class
    )
    suspend fun getEventDetails(eventId: String): Flow<Event>
}
