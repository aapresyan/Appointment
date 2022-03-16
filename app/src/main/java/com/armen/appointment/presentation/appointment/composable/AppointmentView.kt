package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.armen.appointment.presentation.Constants.Companion.TimesMap
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.HorizontalDivider
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.ui.theme.PrimaryBlue

@Composable
fun Appointment(
    viewModel: AppointmentViewModel,
    navController: NavHostController? = null,
    context: Context? = null
) {

    val textState = remember { mutableStateOf(TextFieldValue()) }

    DefaultCard {
        Column(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, bottom = 2.dp),
                text = "Time slots:",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            TimesMap.keys.forEach {
                Text(
                    modifier = Modifier.padding(top = 12.dp, start = 12.dp, bottom = 2.dp),
                    text = it,
                    color = Color.Black,
                    fontSize = 12.sp
                )
                TimesMap[it]?.let { list ->
                    TimeSlotPickerView(slots = list, viewModel = viewModel)
                }
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                val isEnabled = viewModel.isAnyTimeSlotSelected()
                OutlinedButton(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd),
                    onClick = {
                        navController?.popBackStack()
                        Toast.makeText(
                            context,
                            "Doctor booked at ${viewModel.selectedTimeSlot.value} ${textState.value.text}",
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.bookClicked()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = if (isEnabled) PrimaryBlue else Color.Gray),
                    enabled = isEnabled
                ) {
                    Text("Book Appointment", color = Color.White, fontSize = 10.sp)
                }
            }
        }
    }
}
