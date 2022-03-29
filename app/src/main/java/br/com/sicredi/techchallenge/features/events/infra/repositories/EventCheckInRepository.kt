package br.com.sicredi.techchallenge.features.events.infra.repositories

import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import br.com.sicredi.techchallenge.features.events.infra.mappers.EventCheckInToEventCheckInRequestBodyMapper
import kotlinx.coroutines.flow.Flow

class EventCheckInRepository(
    override val eventCheckInApi: IEventCheckInApi
) : IEventCheckInRepository {

    override suspend fun eventCheckIn(eventCheckIn: EventCheckIn): Flow<Boolean> =
        eventCheckInApi.eventCheckIn(
            EventCheckInToEventCheckInRequestBodyMapper().map(
                eventCheckIn
            )
        )

}