package br.com.sicredi.techchallenge.features.events.di

import br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin.EventCheckInApi
import br.com.sicredi.techchallenge.features.events.external.network.api.events.EventsApi
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import org.koin.dsl.module

val koinModuleExternal = module {

    single<IEventsApi> {
        EventsApi()
    }

    single<IEventCheckInApi> {
        EventCheckInApi()
    }
}