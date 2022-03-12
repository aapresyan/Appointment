package com.armen.appointment.domain.usecase

import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class GetDoctors(private val doctorsRepository: DoctorsRepository) {

    operator fun invoke(): Flow<List<Doctor>> {
        return doctorsRepository.getDoctors()
    }
}