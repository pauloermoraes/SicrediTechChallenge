package br.com.sicredi.techchallenge.features.events.globalonfeature

interface SicrediMapper<FROM, TO> {

    fun map(from: FROM): TO
}