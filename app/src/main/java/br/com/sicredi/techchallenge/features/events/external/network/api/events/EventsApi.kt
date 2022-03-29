package br.com.sicredi.techchallenge.features.events.external.network.api.events

import br.com.sicredi.techchallenge.features.events.external.network.api.events.tools.EventsService
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventsResponse
import br.com.sicredi.techchallenge.features.events.external.network.tools.RetrofitTools
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.global.constants.NetworkConstants
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventsApi : IEventsApi {

    override suspend fun getEvents(): Flow<EventsResponse?> {
        return flow {
            val retrofit = RetrofitTools.getRetrofit(
                NetworkConstants.apiUrl,
                EventsService::class
            )
            emit(retrofit.getEvents())
        }
    }

    @Throws(InvalidEventIdException::class)
    override suspend fun getEventDetails(eventId: String): Flow<EventResponse?> {
        return flow {
            if (eventId.isEmpty()) throw InvalidEventIdException()
            val retrofit = RetrofitTools.getRetrofit(
                NetworkConstants.apiUrl,
                EventsService::class
            )
            emit(retrofit.getEventDetails(eventId))
        }
    }
}