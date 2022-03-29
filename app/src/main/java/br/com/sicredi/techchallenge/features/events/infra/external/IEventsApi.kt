package br.com.sicredi.techchallenge.features.events.infra.external

import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventsResponse
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import kotlinx.coroutines.flow.Flow

interface IEventsApi {

    suspend fun getEvents(): Flow<EventsResponse?>

    @Throws(InvalidEventIdException::class)
    suspend fun getEventDetails(eventId: String): Flow<EventResponse?>
}
