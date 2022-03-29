package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import br.com.sicredi.techchallenge.features.events.external.network.bodies.responses.EventResponse
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class GetEventDetailsUseCaseTest {

    @MockK
    private lateinit var iEventsRepository: IEventsRepository
    private lateinit var iGetEventDetailsUseCase: IGetEventDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        iGetEventDetailsUseCase = GetEventDetailsUseCase(iEventsRepository)
    }

    @Test
    fun testExecuteSuccess() {
        runBlocking {
            coEvery {
                iEventsRepository.getEventDetails(any())
            } coAnswers {
                flow {
                    emit(TestEventDetails.eventResponse)
                }
            }

            val result = iGetEventDetailsUseCase.execute(
                "1"
            ).first()
            assertTrue(result.id?.isNotEmpty() == true)
        }
    }

    @Test(expected = InvalidEventIdException::class)
    fun testExecuteInvalidEventIdFailure() {
        runBlocking {
            coEvery {
                iEventsRepository.getEventDetails(any())
            } coAnswers {
                flow {
                    emit(TestEventDetails.eventResponse)
                }
            }

            iGetEventDetailsUseCase.execute(
                ""
            ).first()
        }
    }
}

private object TestEventDetails {
    val eventResponse = EventResponse(
        listOf(),
        1534784400000,
        "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\n\nNa ocasião, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doação:\n- guias e coleiras em bom estado\n- ração (as que mais precisamos no momento são sênior e filhote)\n- roupinhas \n- cobertas \n- remédios dentro do prazo de validade",
        "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        -51.2146267,
        -30.0392981,
        29.99,
        "Feira de adoção de animais na Redenção",
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
}