package com.armen.appointment.domain.usecase

import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository

class GetDoctors(private val doctorsRepository: DoctorsRepository) {

    operator fun invoke(): List<Doctor> {
        return doctorsRepository.getDoctors()
    }
}