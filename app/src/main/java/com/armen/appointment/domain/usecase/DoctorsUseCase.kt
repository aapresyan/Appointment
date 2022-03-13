package com.armen.appointment.domain.usecase

data class DoctorsUseCase(
    val getDoctors: GetDoctors,
    val getDoctor: GetDoctor,
    val updateDoctor: UpdateDoctor,
    val updateDoctors: UpdateDoctors
)