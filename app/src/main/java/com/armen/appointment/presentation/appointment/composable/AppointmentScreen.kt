package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.armen.appointment.presentation.HeaderText

@Composable
fun AppointmentScreen(docId: Int, navHostController: NavHostController, context: Context) {
    Column {
        HeaderText(text = "Appointment")
        Appointment(
            docId,
            navHostController,
            context
        )
    }
}