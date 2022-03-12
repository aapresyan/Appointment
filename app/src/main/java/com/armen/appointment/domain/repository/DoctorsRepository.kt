package com.armen.appointment.domain.repository

import com.armen.appointment.domain.model.Doctor
import kotlinx.coroutines.flow.Flow

interface DoctorsRepository {
    fun getDoctors(): Flow<List<Doctor>>

    suspend fun getDoctorById(id: Int): Doctor?

    suspend fun updateDoctor(doctor: Doctor)

    suspend fun updateDoctors(doctors: List<Doctor>)
}