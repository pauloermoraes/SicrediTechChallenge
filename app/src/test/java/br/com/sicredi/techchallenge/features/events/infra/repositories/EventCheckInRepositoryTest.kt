package br.com.sicredi.techchallenge.features.events.infra.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class EventCheckInRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var iEventCheckInApi: IEventCheckInApi
    private lateinit var iEventCheckInRepository: IEventCheckInRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        iEventCheckInRepository = EventCheckInRepository(iEventCheckInApi)
    }

    @Test
    fun eventCheckInSuccess() {
        runBlocking {
            coEvery {
                iEventCheckInApi.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(true)
                }
            }

            val result = iEventCheckInRepository.eventCheckIn(
                TestEventCheckIn.eventCheckInSuccess
            ).first()

            assertTrue { result }
        }
    }

    @Test
    fun eventCheckInFailure() {
        runBlocking {
            coEvery {
                iEventCheckInApi.eventCheckIn(any())
            } coAnswers {
                flow {
                    emit(false)
                }
            }

            val result = iEventCheckInRepository.eventCheckIn(
                TestEventCheckIn.eventCheckInFailure
            ).first()

            assertFalse { result }
        }
    }
}

private object TestEventCheckIn {
    val eventCheckInSuccess = EventCheckIn(
        "1",
        "Paulo",
        "paulo.e.r.moraes@gmail.com"
    )

    val eventCheckInFailure = EventCheckIn(
        "",
        "",
        ""
    )
}