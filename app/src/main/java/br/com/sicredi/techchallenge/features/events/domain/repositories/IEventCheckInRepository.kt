package br.com.sicredi.techchallenge.features.events.domain.repositories

import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import kotlinx.coroutines.flow.Flow

interface IEventCheckInRepository {
    val eventCheckInApi: IEventCheckInApi

    suspend fun eventCheckIn(eventCheckIn: EventCheckIn): Flow<Boolean>
}
