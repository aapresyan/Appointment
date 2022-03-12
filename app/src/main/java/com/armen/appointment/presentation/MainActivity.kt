package com.armen.appointment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.armen.appointment.presentation.appointment.composable.AppointmentScreen
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import com.armen.appointment.presentation.doctors.Screen
import com.armen.appointment.presentation.doctors.composable.DoctorsScreen
import com.armen.appointment.ui.theme.AppointmentTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DoctorsViewModel by viewModel()

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
                        viewModel.postScreenState(Screen.Doctor)
                        DoctorsScreen(
                            viewModel = viewModel,
                            navHostController = navHostController
                        )
                    }
                    composable(Screen.Appointment.getRoute()) { backStackEntry ->
                        val docId = backStackEntry.arguments?.getString(Screen.Appointment.key)
                        val doc = viewModel.getDoctors()[docId?.toInt()
                            ?: throw RuntimeException("doc not found")]
                        viewModel.postScreenState(Screen.Appointment)
                        viewModel.postSelectedDoctor(doc)
                        AppointmentScreen(
                            doc = doc,
                            navHostController = navHostController,
                            context = applicationContext
                        )
                    }
                }
            }
        }
    }
}
