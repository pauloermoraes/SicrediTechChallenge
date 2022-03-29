package br.com.sicredi.techchallenge.features.events.infra.mappers

import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediMapper
import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.external.network.bodies.requests.EventCheckInRequest

class EventCheckInToEventCheckInRequestBodyMapper :
    SicrediMapper<EventCheckIn, EventCheckInRequest> {

    override fun map(from: EventCheckIn): EventCheckInRequest = EventCheckInRequest(
        from.eventId,
        from.name,
        from.email
    )

}
