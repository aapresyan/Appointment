package com.armen.appointment.presentation

class Constants {
    companion object {
        private val MorningTimes = listOf("09:00", "09:30", "10:00", "10:30", "11:00")
        private val AfternoonTimes = listOf("12:00", "12:30", "13:00", "13:30", "14:00")
        private val EveningTimes = listOf("16:00", "16:30", "17:00", "17:30", "18:00")
        val TimesMap = mapOf("Morning" to MorningTimes, "Afternoon" to AfternoonTimes, "Evening" to EveningTimes)
        val DEFAULT_EXPERIENCE_RANGE = 1f..20f
    }
}

enum class Tab(val value: String) {
    NEAREST("Nearest"), TOP_DOCTORS("Top Doctors"), FILTER("Filter")
}

sealed class Screen(val key: String = "", val name: String) {
    fun getRoute() = if (key == "") name else "{$key}/$name"

    fun createRoute(key: String) = "$key/$name"

    object Doctor : Screen(name = "doctors")

    object Appointment : Screen(key = "docId", name = "appointment")
}