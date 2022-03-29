package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class GetEventsUseCaseTest {

    @MockK
    private lateinit var iEventsRepository: IEventsRepository
    private lateinit var iGetEventsUseCase: IGetEventsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        iGetEventsUseCase = GetEventsUseCase(iEventsRepository)
    }

    @Test
    fun testExecuteSuccess() {
        runBlocking {
            coEvery {
                iEventsRepository.getEvents()
            } coAnswers {
                flow {
                    emit(TestEvents.eventsResponseSuccess)
                }
            }

            val result = iGetEventsUseCase.execute().first()
            assertTrue(result[0].id.isNotEmpty())
        }
    }
}

private object TestEvents {
    val eventsResponseSuccess = Events().apply {
        for (i in 1..5) {
            add(
                Event(
                    i.toString(),
                    listOf(),
                    1534784400000,
                    "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\n\nNa ocasião, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doação:\n- guias e coleiras em bom estado\n- ração (as que mais precisamos no momento são sênior e filhote)\n- roupinhas \n- cobertas \n- remédios dentro do prazo de validade",
                    "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
                    -51.2146267,
                    -30.0392981,
                    29.99,
                    "Feira de adoção de animais na Redenção"
                )
            )
        }

    }
}