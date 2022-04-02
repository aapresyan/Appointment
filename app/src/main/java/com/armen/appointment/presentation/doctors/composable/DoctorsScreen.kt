package com.armen.appointment.presentation.doctors.composable

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.HeaderText
import com.armen.appointment.presentation.Tab
import com.armen.appointment.presentation.appointment.composable.TimingView
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DoctorsScreen(
    viewModel: DoctorsViewModel = getViewModel(),
    navHostController: NavHostController, context: Context?
) {
    Column {
        HeaderText(text = "Doctors")
        DoctorsRadioGroup(viewModel = viewModel)
        if (viewModel.selectedTab.value == Tab.FILTER) {
            Filter(viewModel)
        }
        DoctorsList(viewModel = viewModel, navController = navHostController, context = context)
    }
}

@Composable
private fun DoctorsList(
    navController: NavHostController,
    viewModel: DoctorsViewModel,
    context: Context? = null
) {
    val doctors = viewModel.doctorsList.value
    val dialogState = remember {
        mutableStateOf<Pair<Doctor?, Boolean>>(null to false)
    }
    LazyColumn {
        items(items = doctors) { doctor ->
            DoctorCard(doctor = doctor, navController, showTimings = {
                dialogState.value = doctor to true
            })
        }
    }
    if (dialogState.value.second) {
        context?.let {
            Dialog(onDismissRequest = { dialogState.value = null to false }) {
                DefaultCard {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.padding(start = 32.dp, top = 8.dp, bottom = 12.dp),
                            text = "Timings",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TimingView(
                            context = it,
                            viewModel = getViewModel(),
                            unavailableSlots = dialogState.value.first?.bookedSlots,
                            clickable = false
                        )
                    }
                }
            }
        }
    }
}
