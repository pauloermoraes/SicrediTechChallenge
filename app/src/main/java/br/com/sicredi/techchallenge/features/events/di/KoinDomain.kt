package br.com.sicredi.techchallenge.features.events.di

import br.com.sicredi.techchallenge.features.events.domain.usecases.*
import org.koin.dsl.module

val koinModuleUseCases = module {

    single<IGetEventsUseCase> {
        GetEventsUseCase(get())
    }

    single<IGetEventDetailsUseCase> {
        GetEventDetailsUseCase(get())
    }

    single<IEventCheckInUseCase> {
        EventCheckInUseCase(get())
    }
}