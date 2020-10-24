package com.treyherman.doordashlite.manager.currency

interface CurrencyFormatManager {
    fun formatCurrency(amountCents: Int): String
}
