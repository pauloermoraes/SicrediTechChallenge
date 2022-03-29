package br.com.sicredi.techchallenge.features.events.external.network.api.events.tools

import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {

    @GET("api/events")
    suspend fun getEvents(): EventsResponse

    @GET("api/events/{itemId}")
    suspend fun getEventDetails(@Path("itemId") itemId: String): EventResponse
}