package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.global.exceptions.InvalidEmailException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import br.com.sicredi.techchallenge.global.exceptions.InvalidNameException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class EventCheckInUseCaseTest {

    @MockK
    private lateinit var iEventCheckInRepository: IEventCheckInRepository
    private lateinit var iEventCheckInUseCase: IEventCheckInUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        iEventCheckInUseCase = EventCheckInUseCase(iEventCheckInRepository)
    }

    @Test
    fun testExecuteSuccess() {
        runBlocking {
            coEvery {
                iEventCheckInRepository.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(true)
                }
            }

            val result = iEventCheckInUseCase.execute(
                TestEventCheckIn.eventCheckInSuccess
            ).first()
            assertTrue(result)
        }
    }

    @Test(expected = InvalidEventIdException::class)
    fun testExecuteCheckInEventIdFailure() {
        runBlocking {
            coEvery {
                iEventCheckInRepository.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(false)
                }
            }

            iEventCheckInUseCase.execute(
                TestEventCheckIn.eventCheckInEventIdFailure
            ).first()
        }
    }

    @Test(expected = InvalidEmailException::class)
    fun testExecuteCheckInEmailFailure() {
        runBlocking {
            coEvery {
                iEventCheckInRepository.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(false)
                }
            }

            iEventCheckInUseCase.execute(
                TestEventCheckIn.eventCheckInEmailFailure
            ).first()
        }
    }

    @Test(expected = InvalidNameException::class)
    fun testExecuteCheckInNameFailure() {
        runBlocking {
            coEvery {
                iEventCheckInRepository.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(false)
                }
            }

            iEventCheckInUseCase.execute(
                TestEventCheckIn.eventCheckInNameFailure
            ).first()
        }
    }

}

private object TestEventCheckIn {
    val eventCheckInSuccess = EventCheckIn(
        "1",
        "Paulo",
        "paulo.e.r.moraes@gmail.com"
    )

    val eventCheckInEventIdFailure = EventCheckIn(
        "",
        "Paulo",
        "paulo.e.r.moraes@gmail.com"
    )
    val eventCheckInNameFailure = EventCheckIn(
        "1",
        "",
        "paulo.e.r.moraes@gmail.com"
    )
    val eventCheckInEmailFailure = EventCheckIn(
        "1",
        "Paulo",
        ""
    )
}