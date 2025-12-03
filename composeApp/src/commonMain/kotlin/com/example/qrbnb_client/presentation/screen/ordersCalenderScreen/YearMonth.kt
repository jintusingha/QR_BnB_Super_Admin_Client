package com.example.qrbnb_client.presentation.screen.ordersCalenderScreen

import kotlinx.datetime.Month

data class YearMonth(
    val year: Int,
    val month: Month,
) {
    fun previousMonth(): YearMonth =
        if (month == Month.JANUARY) {
            YearMonth(year - 1, Month.DECEMBER)
        } else {
            YearMonth(year, Month(month.ordinal))
        }

    fun nextMonth(): YearMonth =
        if (month == Month.DECEMBER) {
            YearMonth(year + 1, Month.JANUARY)
        } else {
            YearMonth(year, Month(month.ordinal + 2))
        }
}