package com.armen.appointment.domain.usecase

data class DoctorsUseCase(
    val getDoctors: GetDoctors,
    val getDoctorById: GetDoctor,
    val updateDoctor: UpdateDoctor,
    val updateDoctors: UpdateDoctors
)