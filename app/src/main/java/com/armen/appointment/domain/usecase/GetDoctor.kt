package com.armen.appointment.domain.usecase

import com.armen.appointment.domain.repository.DoctorsRepository

class GetDoctor(private val doctorsRepository: DoctorsRepository) {

    suspend operator fun invoke(id: Int) = doctorsRepository.getDoctorById(id)
}