package com.armen.appointment.data.repository

import com.armen.appointment.data.dao.DoctorsDao
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.domain.repository.DoctorsRepository

class DoctorsRepositoryImpl(private val doctorsDao: DoctorsDao) : DoctorsRepository {
    override fun getDoctors(): List<Doctor> {
        return doctorsDao.getDoctors()
    }
}