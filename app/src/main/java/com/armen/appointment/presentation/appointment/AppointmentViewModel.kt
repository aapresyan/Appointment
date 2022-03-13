package com.armen.appointment.presentation.appointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.usecase.DoctorsUseCase
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val doctorsUseCase: DoctorsUseCase
) : ViewModel() {

    private val _selectedDoctor = mutableStateOf(Doctor())
    val selectedDoctor: State<Doctor> = _selectedDoctor

    private val _selectedTimeSlot = mutableStateOf("")
    val selectedTimeSlot: State<String> = _selectedTimeSlot

    private val _selectedDoctorId = mutableStateOf(-1)

    fun setSelectedId(id: Int) {
        _selectedDoctorId.value = id
        getDoctor()
    }

    fun postSelectedTimeSlot(slot: String) {
        _selectedTimeSlot.value = slot
    }

    fun isTimeSlotAvailable(time: String) =
        !selectedDoctor.value.bookedSlots.contains(time)

    fun bookClicked() {
        val doc = selectedDoctor.value
        viewModelScope.launch {
            doctorsUseCase.updateDoctor(doc.copy(bookedSlots = doc.bookedSlots.toMutableList().apply {
                add(selectedTimeSlot.value)
            }))
        }
    }

    private fun getDoctor() {
        viewModelScope.launch {
            doctorsUseCase.getDoctor(_selectedDoctorId.value)?.let {
                _selectedDoctor.value = it
            }
        }
    }
}