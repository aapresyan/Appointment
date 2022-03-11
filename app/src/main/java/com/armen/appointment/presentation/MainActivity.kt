package com.armen.appointment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import com.armen.appointment.presentation.doctors.Screen
import com.armen.appointment.presentation.doctors.Tab
import com.armen.appointment.presentation.doctors.composable.Appointment
import com.armen.appointment.presentation.doctors.composable.DoctorCard
import com.armen.appointment.presentation.doctors.composable.Filter
import com.armen.appointment.presentation.doctors.composable.HorizontalDivider
import com.armen.appointment.ui.theme.AppointmentTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DoctorsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()

            AppointmentTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFF4C4CF5),
                                    Color(0xFF8C66F3)
                                )
                            )
                        )
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    HorizontalDivider()
                    DoctorText()
                    if (viewModel.screenState.value == Screen.Doctor) { // screen state?
                        DoctorsRadioGroup()
                    }
                    HorizontalDivider()
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.Doctor.name
                    ) {
                        composable(Screen.Doctor.getRoute()) {
                            viewModel.postScreenState(Screen.Doctor)
                            DoctorsList(navHostController)
                        }
                        composable(Screen.Appointment.getRoute()) { backStackEntry ->
                            val docId = backStackEntry.arguments?.getString(Screen.Appointment.key)
                            val doc = viewModel.getDoctors()[docId?.toInt()
                                ?: throw RuntimeException("doc not found")]
                            viewModel.postScreenState(Screen.Appointment)
                            viewModel.postSelectedDoctor(doc)
                            Appointment(
                                doc,
                                navHostController,
                                applicationContext
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun DoctorsList(navController: NavHostController) {
        LazyColumn {
            if (viewModel.selectedTab.value == Tab.FILTER) {
                item {
                    Filter(viewModel)
                }
            }
            val doctors = viewModel.getDoctors()
            itemsIndexed(doctors) { index, doctor ->
                DoctorCard(index, doctor = doctor, navController)
            }
        }
    }

    @Composable
    private fun DoctorText() {
        Text(
            text = viewModel.getHeaderText(),
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
        )
    }

    @Composable
    private fun DoctorsRadioGroup() {
        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            Tab.values().forEach { tab ->
                val isSelected = tab == viewModel.selectedTab.value
                OutlinedButton(
                    onClick = { viewModel.postSelectedTab(tab) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .background(
                            Color.Transparent
                        )
                        .padding(4.dp), border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = if (isSelected)
                            Color.White else Color.Transparent
                    )
                ) {
                    Text(
                        text = tab.value,
                        color = if (isSelected)
                            Color.Blue else Color.Gray,
                    )
                }
            }
        }
    }
}
