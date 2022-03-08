package com.armen.appointment.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.armen.appointment.data.Doctor
import com.armen.appointment.data.DoctorsDao
import com.armen.appointment.data.LocalDoctorsDAO

class DoctorsViewModel : ViewModel() {

    private var dao: DoctorsDao = LocalDoctorsDAO()
    private var filter = Filter(true)

    private val _selectedTab = mutableStateOf(Tab.NEAREST)
    val selectedTab: State<Tab> = _selectedTab // states?

    private val _screenState = mutableStateOf<Screen>(Screen.Doctor)
    val screenState: State<Screen> = _screenState

    private val _selectedDoctor = mutableStateOf(Doctor())
    val selectedDoctor: State<Doctor> = _selectedDoctor

    private val _genderFilter = mutableStateOf<GenderFilter>(GenderFilter.NONE)
    val genderFilter: State<GenderFilter> = _genderFilter // filter

    private val _experienceRangeFilter = mutableStateOf(EXPERIENCE_RANGE)
    val experienceRangeFilter: State<ClosedFloatingPointRange<Float>> = _experienceRangeFilter

    fun postSelectedTab(tab: Tab) {
        _selectedTab.value = tab
    }

    fun postScreenState(screen: Screen) {
        _screenState.value = screen
    }

    fun postSelectedDoctor(doctor: Doctor) {
        _selectedDoctor.value = doctor
    }

    fun postGenderFilter(gender: GenderFilter) {
        _genderFilter.value = gender
    }

    fun postAgeRange(range: ClosedFloatingPointRange<Float>) {
        _experienceRangeFilter.value = range
    }

    fun getDoctors(): List<Doctor> {
        return when (selectedTab.value) {
            Tab.NEAREST -> getNearestDoctors()
            Tab.TOP_DOCTORS -> getTopDoctors()
            Tab.FILTER -> getFilteredDoctors()
        }
    }

    fun getHeaderText() = when (screenState.value) {
        Screen.Appointment -> selectedDoctor.value.name
        else -> "Doctor"
    }

    private fun getFilteredDoctors() = dao.getDoctors().filter { doc ->
        genderFilter.value.filter(doc) &&
                experienceRangeFilter.value.start.toInt() <= doc.experience &&
                doc.experience <= experienceRangeFilter.value.endInclusive.toInt()
    }

    private fun getNearestDoctors() = dao.getDoctors()

    private fun getTopDoctors() = getNearestDoctors().sortedByDescending { it.rating }.take(3)

    companion object {
        val EXPERIENCE_RANGE = 1f..20f
    }
}

enum class GenderFilter {
    NONE,
    MALE,
    FEMALE;

    fun filter(doctor: Doctor): Boolean {
        return if (this == NONE) {
            true
        } else if (this == MALE && doctor.isMale) {
            true
        } else this == FEMALE && !doctor.isMale
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

class Filter(var isMale: Boolean?) {
    fun applyFilter(doctors: List<Doctor>): List<Doctor> {
        return doctors.filter { it.isMale == isMale }
    }
}

