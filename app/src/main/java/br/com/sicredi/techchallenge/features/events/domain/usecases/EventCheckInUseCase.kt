package br.com.sicredi.techchallenge.features.events.domain.usecases

import br.com.sicredi.techchallenge.features.events.domain.models.EventCheckIn
import br.com.sicredi.techchallenge.features.events.domain.repositories.IEventCheckInRepository
import br.com.sicredi.techchallenge.features.events.domain.tools.ValidationTools
import br.com.sicredi.techchallenge.global.exceptions.InvalidEmailException
import br.com.sicredi.techchallenge.global.exceptions.InvalidEventIdException
import br.com.sicredi.techchallenge.global.exceptions.InvalidNameException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface IEventCheckInUseCase {
    val eventCheckInRepository: IEventCheckInRepository

    @Throws(
        InvalidEventIdException::class,
        InvalidEmailException::class,
        InvalidNameException::class
    )
    suspend fun execute(eventCheckIn: EventCheckIn): Flow<Boolean>
}

class EventCheckInUseCase(
    override val eventCheckInRepository: IEventCheckInRepository
) : IEventCheckInUseCase {

    @Throws(
        InvalidEventIdException::class,
        InvalidEmailException::class,
        InvalidNameException::class
    )
    override suspend fun execute(eventCheckIn: EventCheckIn): Flow<Boolean> {
        return flow {
            if (eventCheckIn.eventId.isEmpty()) throw InvalidEventIdException()
            if (eventCheckIn.name.isEmpty() || eventCheckIn.name.length < 3) throw InvalidNameException()
            if (!ValidationTools.checkEmail(eventCheckIn.email)) throw InvalidEmailException()
            emit(eventCheckInRepository.eventCheckIn(eventCheckIn).first())
        }
    }
}