package com.armen.appointment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor")
class Doctor(
    val drawableId: Int = 0,
    val name: String = "",
    val rating: Int = 0,
    val role: String = "",
    val profession: String = "",
    val experience: Int = 0,
    val feedbacks: Int = 0,
    val available: String = "",
    val isMale: Boolean = true,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)