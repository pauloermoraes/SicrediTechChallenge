package br.com.sicredi.techchallenge.features.events.external.network.api.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class EventsResponseApiTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var iEventsApi: IEventsApi

    @Before
    fun setUp() {
        iEventsApi = EventsApi()
    }

    @Test
    fun testGetEventsSuccess() {
        runBlocking {
            val listItems = iEventsApi.getEvents().first()
            listItems
            assertTrue { listItems?.isNotEmpty() == true }
        }
    }

    @Test
    fun testEventDetailsSuccess() {
        runBlocking {
            val eventDetails = iEventsApi.getEventDetails(TestIds.testEventIdSuccess).first()
            assertTrue {
                eventDetails?.id != null
                eventDetails?.description?.isNotEmpty() == true
            }
        }
    }

    @Test(expected = retrofit2.HttpException::class)
    fun testEventDetailsHttpFailure() {
        runBlocking {
            iEventsApi.getEventDetails(TestIds.testInvalidEventIdFailure).first()
        }
    }

    @Test(expected = InvalidEventIdException::class)
    fun testGetEventDetailsInvalidEventIdFailure() {
        runBlocking {
            iEventsApi.getEventDetails(TestIds.testInvalidEventIdFailure2).first()
        }
    }
}

private object TestIds {
    var testEventIdSuccess: String = "1"
    var testInvalidEventIdFailure: String = "200"
    var testInvalidEventIdFailure2: String = ""
}
