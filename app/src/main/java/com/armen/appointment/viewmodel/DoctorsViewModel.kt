package com.armen.appointment.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.armen.appointment.data.Doctor
import com.armen.appointment.data.DoctorsDao
import com.armen.appointment.data.LocalDoctorsDAO

class DoctorsViewModel : ViewModel() {

    private var dao: DoctorsDao = LocalDoctorsDAO()

    private val _selectedTab = mutableStateOf(Tab.NEAREST)
    val selectedTab: State<Tab> = _selectedTab // states?

    private val _screenState = mutableStateOf<Screen>(Screen.Doctor)
    val screenState: State<Screen> = _screenState

    private val _selectedDoctor = mutableStateOf(Doctor())
    val selectedDoctor: State<Doctor> = _selectedDoctor

    fun postSelectedTab(tab: Tab) {
        _selectedTab.value = tab
    }

    fun postScreenState(screen: Screen) {
        _screenState.value = screen
    }

    fun postSelectedDoctor(doctor: Doctor) {
        _selectedDoctor.value = doctor
    }

    fun getDoctors(): List<Doctor> {
        return when (selectedTab.value) {
            Tab.NEAREST -> getNearestDoctors()
            Tab.TOP_DOCTORS -> getTopDoctors()
            Tab.FILTER -> listOf()
        }
    }

    fun getHeaderText() = when (screenState.value) {
        Screen.Appointment -> selectedDoctor.value.name
        else -> "Doctor"
    }

    private fun getNearestDoctors() = dao.getDoctors()

    private fun getTopDoctors() = getNearestDoctors().sortedByDescending { it.rating }.take(3)
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

