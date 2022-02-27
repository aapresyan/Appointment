package com.armen.appointment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFF4C4CF5),
                                    Color(0xFFCC00CC)
                                )
                            )
                        )
                        .padding(32.dp)
                ) {
                    HorizontalDivider()
                    DoctorText()
                    DoctorsRadioGroup()
                    HorizontalDivider()
                    LazyColumn {
                        item {
                            Doctors()
                            Doctors()
                            Doctors()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Doctors() {
        DoctorCard(
            doctor = Doctor(
                drawableId = R.drawable.dr7,
                name = "Dr. James Smith",
                rating = 70
            )
        )
        DoctorCard(
            doctor = Doctor(
                drawableId = R.drawable.cwells2,
                name = "Dr. Marcus Brady",
                rating = 65
            )
        )
        DoctorCard(
            doctor = Doctor(
                drawableId = R.drawable.averma2,
                name = "Dr. Leroy Jenkins",
                rating = 40
            )
        )
    }

    @Composable
    private fun DoctorCard(doctor: Doctor) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(CornerSize(4.dp)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = doctor.drawableId),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp)
                            .height(32.dp)
                    ) {
                        Text(
                            text = doctor.name,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${doctor.rating}%",
                            Modifier.padding(start = 56.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun HorizontalDivider() {
        Divider(color = Color.Gray, thickness = 1.dp)
    }

    @Composable
    private fun DoctorText() {
        Text(
            text = "Doctor",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)
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
        Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
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
