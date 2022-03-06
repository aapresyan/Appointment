package com.armen.appointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DoctorsViewModel : ViewModel() {

    private var dao: DoctorsDao = LocalDoctorsDAO()

    private val _selectedTab = mutableStateOf(Tab.NEAREST)
    val selectedTab: State<Tab> = _selectedTab

    private val _screenState = mutableStateOf<Screen>(Screen.Doctor)
    val screenState: State<Screen> = _screenState

    fun postSelectedTab(tab: Tab) {
        _selectedTab.value = tab
    }

    fun postScreenState(screen: Screen) {
        _screenState.value = screen
    }

    fun getDoctors(): List<Doctor> {
        return when (selectedTab.value) {
            Tab.NEAREST -> getNearestDoctors()
            Tab.TOP_DOCTORS -> getTopDoctors()
            Tab.FILTER -> listOf()
        }
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
