package com.armen.appointment.presentation.appointment.composable

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.presentation.Constants.Companion.TimesMap
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.HorizontalDivider
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.ui.theme.PrimaryBlue

@Composable
fun Appointment(
    viewModel: AppointmentViewModel,
    context: Context
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
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(
                            id = context.resources.getIdentifier(
                                it.toLowerCase(Locale.current),
                                "drawable",
                                context.packageName
                            )
                        ), contentDescription = "time icon",
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 12.dp, start = 12.dp, bottom = 2.dp)
                            .align(Alignment.CenterVertically),
                        text = it,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
                TimesMap[it]?.let { list ->
                    TimeSlotPickerView(slots = list, viewModel = viewModel)
                }
                HorizontalDivider()
            }
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
                        viewModel.bookClicked(textState.value.text)
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
