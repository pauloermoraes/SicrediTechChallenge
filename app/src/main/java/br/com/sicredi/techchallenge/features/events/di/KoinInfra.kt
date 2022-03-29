package br.com.sicredi.techchallenge.features.events.di

import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.features.events.infra.repositories.EventCheckInRepository
import br.com.sicredi.techchallenge.features.events.infra.repositories.EventsRepository
import org.koin.dsl.module

val koinModuleRepositories = module {

    single<IEventsRepository> {
        EventsRepository(get())
    }

    single<IEventCheckInRepository> {
        EventCheckInRepository(get())
    }
}