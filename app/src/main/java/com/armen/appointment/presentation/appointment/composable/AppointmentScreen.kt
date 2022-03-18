package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.armen.appointment.presentation.HeaderText
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.presentation.doctors.composable.DoctorCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun AppointmentScreen(docId: Int, navHostController: NavHostController, context: Context) {

    val viewModel: AppointmentViewModel = getViewModel()
    val doctor = viewModel.selectedDoctor.value
    viewModel.setSelectedId(docId)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            navHostController.popBackStack()
            Toast.makeText(
                context,
                "Doctor booked at ${viewModel.selectedTimeSlot.value} ${it.note}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column {
        HeaderText(text = "Appointment")
        DoctorCard(doctor = doctor, drawButtons = false)
        Appointment(
            viewModel,
            context
        )
    }
}