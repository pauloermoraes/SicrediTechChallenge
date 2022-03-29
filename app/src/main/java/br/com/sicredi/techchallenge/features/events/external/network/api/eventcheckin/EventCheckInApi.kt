package br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin

import br.com.sicredi.techchallenge.features.events.external.network.api.eventcheckin.tools.EventCheckInService
import br.com.sicredi.techchallenge.features.events.external.network.bodies.requests.EventCheckInRequest
import br.com.sicredi.techchallenge.features.events.external.network.tools.RetrofitTools
import br.com.sicredi.techchallenge.features.events.infra.external.IEventCheckInApi
import br.com.sicredi.techchallenge.global.constants.NetworkConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class EventCheckInApi : IEventCheckInApi {

    override suspend fun eventCheckIn(eventCheckInRequest: EventCheckInRequest): Flow<Boolean> {
        return flow {
            val retrofit = RetrofitTools.getRetrofit(
                NetworkConstants.apiUrl,
                EventCheckInService::class
            )
            try {
                retrofit.eventCheckIn(eventCheckInRequest)
                emit(true)
            } catch (e: HttpException) {
                emit(false)
            }

        }
    }
}