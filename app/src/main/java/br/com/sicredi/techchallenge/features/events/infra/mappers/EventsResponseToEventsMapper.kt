package br.com.sicredi.techchallenge.features.events.infra.mappers

import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediMapper
import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventsResponse
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException

class EventsResponseToEventsMapper : SicrediMapper<EventsResponse?, Events> {

    @Throws(EventsConversionException::class)
    override fun map(from: EventsResponse?): Events {
        from?.takeIf { it.isNotEmpty() } ?: throw EventsConversionException()
        return Events().apply {
            from.map { eventResponse ->
                add(
                    Event(
                        eventResponse.id ?: throw EventsConversionException(),
                        eventResponse.people ?: listOf(),
                        eventResponse.date ?: throw EventsConversionException(),
                        eventResponse.description ?: throw EventsConversionException(),
                        eventResponse.image ?: throw EventsConversionException(),
                        eventResponse.longitude ?: throw EventsConversionException(),
                        eventResponse.latitude ?: throw EventsConversionException(),
                        eventResponse.price ?: throw EventsConversionException(),
                        eventResponse.title ?: throw EventsConversionException()
                    )
                )
            }
        }
    }

}
