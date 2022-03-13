package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.armen.appointment.presentation.HeaderText
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.presentation.doctors.composable.DoctorCard
import org.koin.androidx.compose.getViewModel

@Composable
fun AppointmentScreen(docId: Int, navHostController: NavHostController, context: Context) {

    val viewModel: AppointmentViewModel = getViewModel()
    val doctor = viewModel.selectedDoctor.value
    viewModel.setSelectedId(docId)

    Column {
        HeaderText(text = "Appointment")
        DoctorCard(doctor = doctor, drawButtons = false)
        Appointment(
            navHostController,
            context
        )
    }
}