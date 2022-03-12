package com.armen.appointment.presentation.doctors.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.armen.appointment.presentation.HeaderText
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import com.armen.appointment.presentation.doctors.Tab

@Composable
fun DoctorsScreen(viewModel: DoctorsViewModel, navHostController: NavHostController) {
    Column {
        HeaderText(text = "Doctors")
        DoctorsRadioGroup(viewModel = viewModel)
        if (viewModel.selectedTab.value == Tab.FILTER) {
            Filter(viewModel)
        }
        DoctorsList(viewModel = viewModel, navController = navHostController)
    }
}

@Composable
private fun DoctorsList(navController: NavHostController, viewModel: DoctorsViewModel) {
    val doctors = viewModel.getDoctors()
    LazyColumn {
        itemsIndexed(doctors) { index, doctor ->
            DoctorCard(index, doctor = doctor, navController)
        }
    }
}

@Composable
private fun DoctorsRadioGroup(viewModel: DoctorsViewModel) {
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