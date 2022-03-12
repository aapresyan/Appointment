package com.armen.appointment.presentation.doctors

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armen.appointment.data.dao.LocalDoctors
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
            doctorsUseCase.getDoctors().collect {
                if (it.isEmpty()) {
                    doctorsUseCase.updateDoctors(LocalDoctors.AllDoctors)
                }
                _doctorsList.value = when (selectedTab.value) {
                    Tab.NEAREST -> it
                    Tab.TOP_DOCTORS -> getTopDoctors(it)
                    Tab.FILTER -> getFilteredDoctors(it)
                }
            }
        }
    }

    private fun getFilteredDoctors(doctors: List<Doctor>) = filter.value.filterDoctors(doctors)

    private fun getTopDoctors(doctors: List<Doctor>) = doctors.sortedByDescending { it.rating }.take(3)

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


