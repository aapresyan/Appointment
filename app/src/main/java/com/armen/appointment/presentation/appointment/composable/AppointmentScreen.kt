package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.armen.appointment.presentation.HeaderText
import com.armen.appointment.presentation.Screen
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.presentation.doctors.composable.DoctorCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun AppointmentScreen(docId: Int, mainController: NavHostController, context: Context) {

    val viewModel: AppointmentViewModel = getViewModel()
    val doctor = viewModel.selectedDoctor.value
    viewModel.setSelectedId(docId)

    val navHostController = rememberNavController()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AppointmentViewModel.UiCallback.TimingSelectedCallback -> {
                    navHostController.navigate(Screen.UserDetails.name)
                }
                AppointmentViewModel.UiCallback.DoctorBookedCallback -> {
                    mainController.popBackStack()
                }
            }

        }
    }

    Column {
        HeaderText(text = "Appointment")
        DoctorCard(doctor = doctor, drawButtons = false)
        NavHost(navController = navHostController, startDestination = Screen.Timing.name) {
            composable(Screen.Timing.getRoute()) {
                Appointment(
                    viewModel,
                    context
                )
            }
            composable(Screen.UserDetails.getRoute()) {
                UserDetailsView(viewModel)
            }
        }
    }
}