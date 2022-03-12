package com.armen.appointment.data.dao

import com.armen.appointment.R
import com.armen.appointment.domain.model.Doctor

class LocalDoctors {

    companion object {
        val AllDoctors = listOf(
            Doctor(
                drawableId = R.drawable.doc1,
                name = "Dr. James Smith",
                rating = 70,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 10,
                feedbacks = 12,
                available = "Salt lake",
                isMale = false
            ), Doctor(
                drawableId = R.drawable.doc2,
                name = "Dr. Marcus Brady",
                rating = 65,
                role = "MBBS, MB - General Medicine",
                profession = "Number #1 bullshit guy",
                experience = 9,
                feedbacks = 13,
                available = "Glen Park",
                isMale = false
            ), Doctor(
                drawableId = R.drawable.doc3,
                name = "Dr. Leroy Jenkins",
                rating = 40,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 5,
                feedbacks = 20,
                available = "Nowhere",
                isMale = true
            ), Doctor(
                drawableId = R.drawable.doc4,
                name = "Dr. Ivana Johnson",
                rating = 60,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 6,
                feedbacks = 15,
                available = "Somewhere",
                isMale = false
            ), Doctor(
                drawableId = R.drawable.doc5,
                name = "Dr. Michael Jackson",
                rating = 56,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 3,
                feedbacks = 9,
                available = "LA",
                isMale = true
            ), Doctor(
                drawableId = R.drawable.doc6,
                name = "Dr. Anooshig",
                rating = 89,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 4,
                feedbacks = 8,
                available = "Yerevan",
                isMale = false
            ), Doctor(
                drawableId = R.drawable.doc7,
                name = "Dr. Neighbor's mother",
                rating = 50,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 6,
                feedbacks = 20,
                available = "Chicago DL",
                isMale = false
            )
        )
    }
}
