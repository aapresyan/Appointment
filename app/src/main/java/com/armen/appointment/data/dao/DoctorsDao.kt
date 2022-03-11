package com.armen.appointment.data.dao

import com.armen.appointment.domain.model.Doctor

interface DoctorsDao {
    fun getDoctors(): List<Doctor>
}