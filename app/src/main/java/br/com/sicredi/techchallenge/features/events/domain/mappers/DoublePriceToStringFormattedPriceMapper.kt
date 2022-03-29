package br.com.sicredi.techchallenge.features.events.domain.mappers

import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediMapper
import java.text.NumberFormat
import java.util.*

class DoublePriceToStringFormattedPriceMapper : SicrediMapper<Double, String> {
    override fun map(from: Double): String = NumberFormat.getCurrencyInstance(
        Locale("pt", "BR")
    ).format(from)
}