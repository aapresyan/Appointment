package com.armen.appointment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.armen.appointment.presentation.appointment.composable.AppointmentScreen
import com.armen.appointment.presentation.doctors.composable.DoctorsScreen
import com.armen.appointment.ui.theme.AppointmentTheme
import com.armen.appointment.ui.theme.PrimaryBlue
import com.armen.appointment.ui.theme.SecondaryBlue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppointmentTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = Screen.Doctor.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    PrimaryBlue,
                                    SecondaryBlue
                                )
                            )
                        )
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    composable(Screen.Doctor.getRoute()) {
                        DoctorsScreen(
                            navHostController = navHostController, context = applicationContext
                        )
                    }
                    composable(Screen.Appointment.getRoute()) { backStackEntry ->
                        val docId =
                            backStackEntry.arguments?.getString(Screen.Appointment.key)?.toInt()
                                ?: -1
                        AppointmentScreen(
                            docId = docId,
                            mainController = navHostController,
                            context = applicationContext
                        )
                    }
                }
            }
        }
    }
}
