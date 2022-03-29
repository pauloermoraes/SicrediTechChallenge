package br.com.sicredi.techchallenge.features.events.external.network.bodies.requests

import com.google.gson.annotations.SerializedName

data class EventCheckInRequest(
    @SerializedName("eventId")
    val eventId: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)