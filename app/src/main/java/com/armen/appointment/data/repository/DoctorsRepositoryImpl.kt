package com.armen.appointment.data.repository

import com.armen.appointment.data.dao.DoctorsDao
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class DoctorsRepositoryImpl(private val doctorsDao: DoctorsDao) : DoctorsRepository {
    override fun getDoctors(): Flow<List<Doctor>> {
        return doctorsDao.getDoctors()
    }

    override suspend fun getDoctorById(id: Int): Doctor? {
        return doctorsDao.getDoctorById(id)
    }

    override suspend fun updateDoctor(doctor: Doctor) {
        doctorsDao.updateDoctor(doctor)
    }

    override suspend fun updateDoctors(doctors: List<Doctor>) {
        doctorsDao.updateDoctors(doctors)
    }
}