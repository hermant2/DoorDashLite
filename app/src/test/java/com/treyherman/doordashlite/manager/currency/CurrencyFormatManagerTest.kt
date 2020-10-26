package com.treyherman.doordashlite.manager.currency

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFormatManagerTest {

    private val currencyFormatManager: CurrencyFormatManager by lazy {
        CurrencyFormatManagerImpl()
    }

    @Test
    fun formatCurrency_twoHundredCents_result() {
        val formattedCurrency = currencyFormatManager.formatCurrency(200)
        assertEquals(formattedCurrency, "$2.00")
    }

    @Test
    fun formatCurrency_zeroCents_result() {
        val formattedCurrency = currencyFormatManager.formatCurrency(0)
        assertEquals(formattedCurrency, "$0.00")
    }

    @Test
    fun formatCurrency_oneHundredFiftyCents_result() {
        val formattedCurrency = currencyFormatManager.formatCurrency(150)
        assertEquals(formattedCurrency, "$1.50")
    }
}