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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
                                    Color(0xFF8C66F3)
                                )
                            )
                        )
                        .padding(20.dp)
                ) {
                    HorizontalDivider()
                    DoctorText()
                    DoctorsRadioGroup()
                    HorizontalDivider()
                    LazyColumn {
                        item {
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
                rating = 70,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 10,
                feedbacks = 12,
                available = "Salt lake"
            )
        )
        DoctorCard(
            doctor = Doctor(
                drawableId = R.drawable.cwells2,
                name = "Dr. Marcus Brady",
                rating = 65,
                role = "MBBS, MB - General Medicine",
                profession = "Number #1 bullshit guy",
                experience = 10,
                feedbacks = 13,
                available = "Glen Park"
            )
        )
        DoctorCard(
            doctor = Doctor(
                drawableId = R.drawable.averma2,
                name = "Dr. Leroy Jenkins",
                rating = 40,
                role = "MBBS, MB - General Medicine",
                profession = "General Physician",
                experience = 5,
                feedbacks = 20,
                available = "Nowhere"
            )
        )
    }

    @Preview
    @Composable
    private fun DoctorCardPreview() {
        val doctor = Doctor(
            drawableId = R.drawable.averma2,
            name = "Dr. Leroy Jenkins",
            rating = 40,
            role = "MBBS, MB - General Medicine",
            profession = "General Physician",
            experience = 5,
            feedbacks = 20,
            available = "Nowhere"
        )
        DoctorCard(doctor)
    }

    @Composable
    private fun DoctorCard(doctor: Doctor) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(CornerSize(12.dp)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            backgroundColor = Color.White
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(0.6f)
                        ) {
                            Text(
                                text = doctor.name,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterStart),
                                maxLines = 1
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${doctor.rating}%",
                                fontSize = 12.sp,
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterEnd),
                                textAlign = TextAlign.Right,
                                maxLines = 1
                            )
                        }
                    }
                    Text(doctor.role, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                    Text(doctor.profession, fontSize = 10.sp)
                    HorizontalDivider()
                    Row {
                        Text("${doctor.experience} years of experience", fontSize = 10.sp)
                        Text(
                            "${doctor.feedbacks} feedbacks",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(start = 24.dp)
                        )
                    }
                    HorizontalDivider()
                    Text(
                        buildAnnotatedString {
                            append("Available Tomorrow at ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(doctor.available)
                            }
                        }, fontSize = 12.sp
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                        ) {
                            Text("Timing", color = Color.Gray, fontSize = 10.sp)
                        }
                        OutlinedButton(
                            onClick = { },
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

    @Composable
    private fun HorizontalDivider() {
        Divider(color = Color.Gray)
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
