package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.data.dao.LocalDoctorsDAO
import com.armen.appointment.presentation.doctors.composable.DoctorCard


@Preview
@Composable
private fun AppointmentPreview() {
    val docDao = LocalDoctorsDAO()
    Appointment(docDao.getDoctors()[0])
}


@Composable
fun Appointment(
    doctor: Doctor,
    navController: NavHostController? = null,
    context: Context? = null
) {
    Column {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        DoctorCard(doctor = doctor, drawButtons = false)
        Card(
            shape = RoundedCornerShape(CornerSize(12.dp)), elevation = 8.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            backgroundColor = Color.White
        ) {
            Column(Modifier.fillMaxWidth()) {
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text(text = "Note") },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray
                    ),

                    )
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterEnd),
                        onClick = {
                            navController?.popBackStack()
                            Toast.makeText(
                                context,
                                textState.value.text,
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Blue)
                    ) {
                        Text("Book Appointment", color = Color.White, fontSize = 10.sp)
                    }
                }
            }

        }
    }
}