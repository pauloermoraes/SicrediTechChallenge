package br.com.sicredi.techchallenge.features.events.infra.external

import br.com.sicredi.techchallenge.features.events.external.network.bodies.requests.EventCheckInRequest
import kotlinx.coroutines.flow.Flow

interface IEventCheckInApi {
    suspend fun eventCheckIn(eventCheckInRequest: EventCheckInRequest): Flow<Boolean>
}
