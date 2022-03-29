package br.com.sicredi.techchallenge.features.events.di

import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.features.events.domain.usecases.*
import br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin.EventCheckInApi
import br.com.sicredi.techchallenge.features.events.external.network.api.events.EventsApi
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.features.events.infra.repositories.EventCheckInRepository
import br.com.sicredi.techchallenge.features.events.infra.repositories.EventsRepository
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class SicrediKoinTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        loadKoinModules(
            listOf(
                koinModuleUseCases,
                koinModuleRepositories,
                koinModuleExternal
            )
        )
    }

    //region Domain

    @Test
    fun testGetEventsUseCase() {
        val iGetEventsUseCase by inject<IGetEventsUseCase>()
        assert(iGetEventsUseCase is GetEventsUseCase)
    }

    @Test
    fun testGetEventDetailsUseCase() {
        val iGetEventDetailsUseCase by inject<IGetEventDetailsUseCase>()
        assert(iGetEventDetailsUseCase is GetEventDetailsUseCase)
    }

    @Test
    fun testEventCheckInUseCase() {
        val iEventCheckInUseCase by inject<IEventCheckInUseCase>()
        assert(iEventCheckInUseCase is EventCheckInUseCase)
    }

    //endregion Domain

    //region Infra

    @Test
    fun testEventsRepository() {
        val iEventsRepository by inject<IEventsRepository>()
        assert(iEventsRepository is EventsRepository)
    }

    @Test
    fun testEventCheckInRepository() {
        val iEventCheckInRepository by inject<IEventCheckInRepository>()
        assert(iEventCheckInRepository is EventCheckInRepository)
    }

    //endregion Infra

    //region External

    @Test
    fun testEventsApi() {
        val iEventsApi by inject<IEventsApi>()
        assert(iEventsApi is EventsApi)
    }

    @Test
    fun testEventCheckInApi() {
        val iEventCheckInApi by inject<IEventCheckInApi>()
        assert(iEventCheckInApi is EventCheckInApi)
    }

    //endregion External
}

