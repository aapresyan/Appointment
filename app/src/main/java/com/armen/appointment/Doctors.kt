package com.armen.appointment

val NearestDoctors = listOf(
    Doctor(
        drawableId = R.drawable.dr7,
        name = "Dr. James Smith",
        rating = 70,
        role = "MBBS, MB - General Medicine",
        profession = "General Physician",
        experience = 10,
        feedbacks = 12,
        available = "Salt lake"
    ), Doctor(
        drawableId = R.drawable.cwells2,
        name = "Dr. Marcus Brady",
        rating = 65,
        role = "MBBS, MB - General Medicine",
        profession = "Number #1 bullshit guy",
        experience = 10,
        feedbacks = 13,
        available = "Glen Park"
    ), Doctor(
        drawableId = R.drawable.averma2,
        name = "Dr. Leroy Jenkins",
        rating = 40,
        role = "MBBS, MB - General Medicine",
        profession = "General Physician",
        experience = 5,
        feedbacks = 20,
        available = "Nowhere"
    )
)
// add Top doctors
// get the list of top doctors from viewModel