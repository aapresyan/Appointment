package com.armen.appointment.domain.usecase

import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository

class UpdateDoctors(private val doctorsRepository: DoctorsRepository) {

    suspend operator fun invoke(doctors: List<Doctor>) = doctorsRepository.updateDoctors(doctors)
}