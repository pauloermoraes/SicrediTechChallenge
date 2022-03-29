package br.com.sicredi.techchallenge.features.events.infra.mappers

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediMapper
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException

class EventResponseToEventMapper : SicrediMapper<EventResponse?, Event> {
    override fun map(from: EventResponse?): Event {
        from ?: throw EventsConversionException()
        return Event(
            from.id ?: throw EventsConversionException(),
            from.people ?: listOf(),
            from.date ?: throw EventsConversionException(),
            from.description ?: throw EventsConversionException(),
            from.image ?: throw EventsConversionException(),
            from.longitude ?: throw EventsConversionException(),
            from.latitude ?: throw EventsConversionException(),
            from.price ?: throw EventsConversionException(),
            from.title ?: throw EventsConversionException()
        )
    }

}
