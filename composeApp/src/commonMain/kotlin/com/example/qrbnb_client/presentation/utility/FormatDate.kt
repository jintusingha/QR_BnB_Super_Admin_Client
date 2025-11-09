package com.example.qrbnb_client.presentation.utility

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun formatDate(isoDateString: String): String {
    return try {
        val instant = Instant.parse(isoDateString)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val month = when (localDateTime.monthNumber) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> ""
        }

        "$month ${localDateTime.dayOfMonth}, ${localDateTime.year}"
    } catch (e: Exception) {
        isoDateString
    }
}