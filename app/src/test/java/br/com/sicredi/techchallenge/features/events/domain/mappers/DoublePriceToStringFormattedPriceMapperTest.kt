package br.com.sicredi.techchallenge.features.events.domain.mappers

import org.junit.Test

import org.junit.Before

class DoublePriceToStringFormattedPriceMapperTest {
    private lateinit var doublePriceToStringFormattedPriceMapper: DoublePriceToStringFormattedPriceMapper

    @Before
    fun setUp() {
        doublePriceToStringFormattedPriceMapper = DoublePriceToStringFormattedPriceMapper()
    }

    @Test
    fun map() {
        val mappedValue = doublePriceToStringFormattedPriceMapper.map(29.99)
        assert("R\$ 29,99" == mappedValue)
    }
}