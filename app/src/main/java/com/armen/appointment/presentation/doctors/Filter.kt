package com.armen.appointment.presentation.doctors

import com.armen.appointment.domain.model.Doctor

enum class Gender {
    NONE,
    MALE,
    FEMALE;

    fun filter(doctor: Doctor): Boolean {
        return if (this == NONE) {
            true
        } else if (this == MALE && doctor.isMale) {
            true
        } else this == FEMALE && !doctor.isMale
    }
}

data class Filter(val gender: Gender, val range: ClosedFloatingPointRange<Float>) {
    fun filterDoctors(doctors: List<Doctor>): List<Doctor> {
        return doctors.filter { gender.filter(it) &&
                range.start.toInt() <= it.experience &&
                it.experience <= range.endInclusive.toInt() }
    }

}
