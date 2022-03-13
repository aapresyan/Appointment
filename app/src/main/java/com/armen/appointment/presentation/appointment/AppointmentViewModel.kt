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

    private val _selectedDoctorId = mutableStateOf(-1)

    fun setSelectedId(id: Int) {
        _selectedDoctorId.value = id
        getDoctor()
    }

    private fun getDoctor() {
        viewModelScope.launch {
            doctorsUseCase.getDoctor(_selectedDoctorId.value)?.let {
                _selectedDoctor.value = it
            }
        }
    }
}