package br.com.sicredi.techchallenge.features.events.infra.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventsResponse
import br.com.sicredi.techchallenge.features.events.infra.external.IEventsApi
import br.com.sicredi.techchallenge.global.exceptions.EventsConversionException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventDetailsException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventsException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class EventsRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var iEventsApi: IEventsApi
    private lateinit var iEventsRepository: IEventsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        iEventsRepository = EventsRepository(iEventsApi)
    }

    @Test
    fun testGetEventsSuccess() {
        runBlocking {
            coEvery {
                iEventsApi.getEvents()
            } coAnswers {
                flow { emit(TestEvents.eventsResponseSuccess) }
            }
            val events = iEventsRepository.getEvents().first()
            assertTrue(events.isNotEmpty())
        }
    }

    @Test(expected = InvalidEventsException::class)
    fun testGetEventsFailure() {
        runBlocking {
            coEvery {
                iEventsApi.getEvents()
            } coAnswers {
                flow { emit(TestEvents.eventsResponseFailure) }
            }
            iEventsRepository.getEvents().first()
        }
    }

    @Test(expected = EventsConversionException::class)
    fun testGetEventsConversionFailure() {
        runBlocking {
            coEvery {
                iEventsApi.getEvents()
            } coAnswers {
                flow { emit(TestEvents.eventsResponseConversionFailure) }
            }
            iEventsRepository.getEvents().first()
        }
    }

    @Test
    fun testGetEventDetailsSuccess() {
        runBlocking {
            coEvery {
                iEventsApi.getEventDetails(any())
            } coAnswers {
                flow { emit(TestEventDetails.eventResponseSuccess) }
            }
            val result = iEventsRepository.getEventDetails("1").first()
            assertTrue { result.id?.isNotEmpty() == true }
        }
    }

    @Test(expected = InvalidEventIdException::class)
    fun testEventDetailsInvalidEventIdFailure() {
        runBlocking {
            coEvery {
                iEventsApi.getEventDetails(any())
            } coAnswers {
                flow { emit(TestEventDetails.eventResponseConversionFailure) }
            }
            iEventsRepository.getEventDetails("").first()
        }
    }

    @Test(expected = InvalidEventDetailsException::class)
    fun testGetEventDetailsFailure() {
        runBlocking {
            coEvery {
                iEventsApi.getEventDetails(any())
            } coAnswers {
                flow { emit(TestEventDetails.eventResponseFailure) }
            }
            iEventsRepository.getEventDetails("1").first()
        }
    }

}

private object TestEvents {
    val eventsResponseSuccess = EventsResponse().apply {
        for (i in 1..5) {
            add(
                EventResponse(
                    listOf(),
                    1534784400000,
                    "O Patas Dadas estar?? na Reden????o, nesse domingo, com c??es para ado????o e produtos ?? venda!\n\nNa ocasi??o, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estar??o prontinhos para ganhar o ??? de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doa????o:\n- guias e coleiras em bom estado\n- ra????o (as que mais precisamos no momento s??o s??nior e filhote)\n- roupinhas \n- cobertas \n- rem??dios dentro do prazo de validade",
                    "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
                    -51.2146267,
                    -30.0392981,
                    29.99,
                    "Feira de ado????o de animais na Reden????o",
                    i.toString()
                )
            )
        }

    }

    val eventsResponseFailure = EventsResponse()

    val eventsResponseConversionFailure = EventsResponse().apply {
        add(
            EventResponse(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
    }
}

private object TestEventDetails {
    val eventResponseSuccess = EventResponse(
        listOf(),
        1534784400000,
        "O Patas Dadas estar?? na Reden????o, nesse domingo, com c??es para ado????o e produtos ?? venda!\n\nNa ocasi??o, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estar??o prontinhos para ganhar o ??? de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doa????o:\n- guias e coleiras em bom estado\n- ra????o (as que mais precisamos no momento s??o s??nior e filhote)\n- roupinhas \n- cobertas \n- rem??dios dentro do prazo de validade",
        "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        -51.2146267,
        -30.0392981,
        29.99,
        "Feira de ado????o de animais na Reden????o",
        "1"
    )

    val eventResponseFailure = EventResponse(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    val eventResponseConversionFailure = EventResponse(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}