package com.armen.appointment.presentation.appointment.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.ui.theme.PrimaryBlue

@Composable
fun TimeSlotPickerView(
    slots: List<String>,
    clickable: Boolean = true,
    unavailableSlots: List<String>? = null,
    viewModel: AppointmentViewModel? = null
) {

    LazyRow {
        items(slots) { time ->
            val isSelected = viewModel?.selectedTimeSlot?.value == time
            val isAvailable = unavailableSlots?.contains(time)?.not() ?: viewModel?.isTimeSlotAvailable(time) == true

            OutlinedButton(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .background(Color.Transparent),
                onClick = { if (isAvailable) viewModel?.postSelectedTimeSlot(time) },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent
                ),
                enabled = clickable,
                border = BorderStroke(
                    1.dp,
                    color = if (isSelected) PrimaryBlue else Color.Transparent
                )
            ) {
                Text(
                    text = time,
                    textAlign = TextAlign.Center,
                    color = if (!isAvailable) Color.Red else if (isSelected) PrimaryBlue else Color.Gray,
                    fontSize = 10.sp
                )
            }
        }
    }
}
