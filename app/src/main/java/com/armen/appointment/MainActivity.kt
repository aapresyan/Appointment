package com.armen.appointment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.ui.theme.AppointmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppointmentTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Blue,
                                    Color(0xFFCC00CC)
                                )
                            )
                        )
                ) {
                    DoctorText()
                    DoctorsRadioGroup()
                }
            }
        }
    }

    @Composable
    private fun DoctorText() {
        Text(
            text = "Doctor",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 32.dp, top = 64.dp)
        )
    }

    //hold all UI data in ViewModel and update correspondingly
    @Preview
    @Composable
    private fun DoctorsRadioGroup() {
        val selected = remember { mutableStateOf("Nearest") }

        val options = listOf("Nearest", "Top Doctors", "Filter")
        val onSelected = { text: String ->
            selected.value = text
        }
        Row(modifier = Modifier.padding(start = 24.dp, top = 128.dp)) {
            options.forEach { text ->
                val isSelected = text == selected.value
                OutlinedButton(
                    onClick = { onSelected(text) },
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
                        text = text,
                        color = if (isSelected)
                            Color.Blue else Color.Gray,
                    )
                }
            }
        }
    }
}
