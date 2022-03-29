package br.com.sicredi.techchallenge.features.events.domain.tools

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS

object ValidationTools {

    fun checkEmail(email: String) = EMAIL_ADDRESS.matcher(email).matches()

}

