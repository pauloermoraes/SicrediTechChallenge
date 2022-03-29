package br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin.tools

import br.com.sicredi.techchallenge.features.events.external.network.bodies.requests.EventCheckInRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EventCheckInService {

    @POST("api/checkin")
    suspend fun eventCheckIn(
        @Body eventCheckInRequest: EventCheckInRequest
    )
}