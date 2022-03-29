package br.com.sicredi.techchallenge.features.events.domain.models

data class Event(
    val id: String,
    val people: List<Any?>?,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String
)