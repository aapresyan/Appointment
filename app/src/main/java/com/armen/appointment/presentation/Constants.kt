package com.armen.appointment.presentation

class Constants {
    companion object {
        val TimeSlots = listOf("09:00", "09:30", "10:00", "10:30", "11:00")
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