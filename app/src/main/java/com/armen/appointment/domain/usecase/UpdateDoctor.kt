package com.armen.appointment.domain.usecase

import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository

class UpdateDoctor(private val doctorsRepository: DoctorsRepository) {
    suspend operator fun invoke(doctor: Doctor) = doctorsRepository.updateDoctor(doctor)
}