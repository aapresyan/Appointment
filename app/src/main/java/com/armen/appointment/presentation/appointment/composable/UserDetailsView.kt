package com.armen.appointment.presentation.appointment.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.Option
import com.armen.appointment.presentation.TextFieldWithHint
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.ui.theme.PrimaryBlue

@Composable
fun UserDetailsView(viewModel: AppointmentViewModel) {
    DefaultCard {
        Column(Modifier.padding(16.dp)) {
            val user = viewModel.userDetails.value
            TextFieldWithHint(state = user.name, hint = "Name", icon = Icons.Outlined.Person)
            TextFieldWithHint(
                state = user.phone,
                hint = "Phone",
                keyboardType = KeyboardType.Phone,
                icon = Icons.Outlined.Phone
            )
            TextFieldWithHint(
                state = user.email,
                hint = "Email",
                keyboardType = KeyboardType.Email,
                icon = Icons.Outlined.Email
            )
            Row {
                TextFieldWithHint(
                    modifier = Modifier.width(102.dp),
                    state = user.age,
                    hint = "Age",
                    keyboardType = KeyboardType.Number,
                )
                Option(
                    text = "Male",
                    isChecked = user.isMale.value
                ) { user.isMale.value = true }
                Option(
                    text = "Female",
                    isChecked = !user.isMale.value
                ) { user.isMale.value = false }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        viewModel.bookClicked()
                    },
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = PrimaryBlue)

                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Confirm",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}