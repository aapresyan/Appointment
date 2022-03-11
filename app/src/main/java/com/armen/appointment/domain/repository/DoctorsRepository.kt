package com.armen.appointment.domain.repository

import com.armen.appointment.domain.model.Doctor

interface DoctorsRepository {
    fun getDoctors(): List<Doctor>
}