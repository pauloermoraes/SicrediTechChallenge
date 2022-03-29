package br.com.sicredi.techchallenge.features.events.domain.mappers

import br.com.sicredi.techchallenge.features.events.globalonfeature.SicrediMapper
import java.text.SimpleDateFormat
import java.util.*

class LongDateToStringFormattedDateMapper : SicrediMapper<Long, String> {
    override fun map(from: Long): String {
        return SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date(from))
    }
}