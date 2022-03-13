package com.armen.appointment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.armen.appointment.presentation.appointment.composable.AppointmentScreen
import com.armen.appointment.presentation.doctors.composable.DoctorsScreen
import com.armen.appointment.ui.theme.AppointmentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppointmentTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = Screen.Doctor.name,
                    modifier = Modifier.mainThemeModifier()
                ) {
                    composable(Screen.Doctor.getRoute()) {
                        DoctorsScreen(
                            navHostController = navHostController
                        )
                    }
                    composable(Screen.Appointment.getRoute()) { backStackEntry ->
                        val docId =
                            backStackEntry.arguments?.getString(Screen.Appointment.key)?.toInt()
                                ?: -1
//                        val doc = viewModel.doctorsList.value[docId?.toInt()
//                            ?: throw RuntimeException("doc not found")]
                        AppointmentScreen(
                            docId = docId,
                            navHostController = navHostController,
                            context = applicationContext
                        )
                    }
                }
            }
        }
    }
}
