package com.treyherman.doordashlite.manager.currency

import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class CurrencyFormatManagerImpl @Inject constructor() : CurrencyFormatManager {
    companion object {
        private const val CENTS_IN_DOLLAR = 100.0
    }

    private val currencyFormatInstance = NumberFormat.getCurrencyInstance(Locale.US)

    override fun formatCurrency(amountCents: Int): String =
        currencyFormatInstance.format(amountCents / CENTS_IN_DOLLAR)
}
