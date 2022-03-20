package com.armen.appointment.presentation.appointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.model.UserDetailsState
import com.armen.appointment.domain.usecase.DoctorsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val doctorsUseCase: DoctorsUseCase
) : ViewModel() {

    private val _selectedDoctor = mutableStateOf(Doctor())
    val selectedDoctor: State<Doctor> = _selectedDoctor

    private val _selectedTimeSlot = mutableStateOf("")
    val selectedTimeSlot: State<String> = _selectedTimeSlot

    private val selectedDoctorId = mutableStateOf(-1)

    private val _eventFlow = MutableSharedFlow<UiCallback>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _userDetails = mutableStateOf(
        UserDetailsState(
            mutableStateOf(TextFieldValue()),
            mutableStateOf(TextFieldValue()),
            mutableStateOf(TextFieldValue()),
            mutableStateOf(TextFieldValue()),
            mutableStateOf(true)
        )
    )
    val userDetails: State<UserDetailsState> = _userDetails

    fun setSelectedId(id: Int) {
        selectedDoctorId.value = id
        getDoctor()
    }

    fun postSelectedTimeSlot(slot: String) {
        _selectedTimeSlot.value = slot
    }

    fun isTimeSlotAvailable(time: String) =
        !selectedDoctor.value.bookedSlots.contains(time)

    fun isAnyTimeSlotSelected() = selectedTimeSlot.value.isNotEmpty()

    fun timingConfirmed() {
        viewModelScope.launch {
            _eventFlow.emit(UiCallback.TimingSelectedCallback)
        }
    }

    fun bookClicked() {
        val doc = selectedDoctor.value
        viewModelScope.launch {
            doctorsUseCase.updateDoctor(
                doc.copy(
                    bookedSlots = doc.bookedSlots.toMutableList().apply {
                        add(selectedTimeSlot.value)
                    })
            )
            _eventFlow.emit(UiCallback.DoctorBookedCallback)
        }
    }

    private fun getDoctor() {
        viewModelScope.launch {
            doctorsUseCase.getDoctor(selectedDoctorId.value)?.let {
                _selectedDoctor.value = it
            }
        }
    }

    sealed class UiCallback {

        object TimingSelectedCallback : UiCallback()

        object DoctorBookedCallback : UiCallback()
    }
}