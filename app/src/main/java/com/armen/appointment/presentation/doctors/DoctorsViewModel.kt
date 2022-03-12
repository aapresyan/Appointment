package com.armen.appointment.presentation.doctors

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.usecase.DoctorsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DoctorsViewModel(private val doctorsUseCase: DoctorsUseCase) : ViewModel() {

    private val _selectedTab = mutableStateOf(Tab.NEAREST)
    val selectedTab: State<Tab> = _selectedTab

    private val _filter = mutableStateOf(Filter(Gender.NONE, EXPERIENCE_RANGE))
    val filter: State<Filter> = _filter

    private val _doctorsList = mutableStateOf(listOf<Doctor>())
    val doctorsList: State<List<Doctor>> = _doctorsList

    private var getDoctorsJob: Job? = null

    init {
        getDoctors()
    }

    fun postSelectedTab(tab: Tab) {
        _selectedTab.value = tab
        getDoctors()
    }

    fun postGenderFilter(gender: Gender) {
        _filter.value = filter.value.copy(gender = gender)
        getDoctors()
    }

    fun postAgeRange(range: ClosedFloatingPointRange<Float>) {
        _filter.value = filter.value.copy(range = range)
        getDoctors()
    }

    private fun getDoctors() {
        getDoctorsJob?.cancel()
        getDoctorsJob = viewModelScope.launch {
            _doctorsList.value = when (selectedTab.value) {
                Tab.NEAREST -> getNearestDoctors()
                Tab.TOP_DOCTORS -> getTopDoctors()
                Tab.FILTER -> getFilteredDoctors()
            }
        }
    }

    private fun getFilteredDoctors() = filter.value.filterDoctors(doctorsUseCase.getDoctors())

    private fun getNearestDoctors() = doctorsUseCase.getDoctors()

    private fun getTopDoctors() = getNearestDoctors().sortedByDescending { it.rating }.take(3)

    companion object {
        val EXPERIENCE_RANGE = 1f..20f
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


