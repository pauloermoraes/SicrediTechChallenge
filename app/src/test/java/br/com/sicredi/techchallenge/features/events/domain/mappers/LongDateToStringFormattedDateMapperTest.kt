package br.com.sicredi.techchallenge.features.events.domain.mappers

import org.junit.Test

import org.junit.Before

class LongDateToStringFormattedDateMapperTest {
    private lateinit var longDateToStringFormattedDateMapper: LongDateToStringFormattedDateMapper

    @Before
    fun setUp() {
        longDateToStringFormattedDateMapper = LongDateToStringFormattedDateMapper()
    }

    @Test
    fun map() {
        val map = longDateToStringFormattedDateMapper.map(1534784400000)
        assert("20/08/2018" == map)
    }
}