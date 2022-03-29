package br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.sicredi.techchallenge.features.events.external.network.bodies.requests.EventCheckInRequest
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventCheckInApiTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var iEventCheckInApi: IEventCheckInApi

    @Before
    fun setUp() {
        iEventCheckInApi = EventCheckInApi()
    }

    @Test
    fun testEventCheckIn() {
        runBlocking {
            val result = iEventCheckInApi.eventCheckIn(
                TestEventCheckIn.eventCheckInRequest
            ).first()
            assertTrue(result)
        }
    }
}

private object TestEventCheckIn {
    val eventCheckInRequest = EventCheckInRequest(
        "1",
        "Paulo",
        "paulo.e.r.moraes@gmail.com"
    )
}