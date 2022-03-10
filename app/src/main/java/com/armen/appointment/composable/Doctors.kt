package com.armen.appointment.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavHostController
import com.armen.appointment.R
import com.armen.appointment.data.Doctor
import com.armen.appointment.data.LocalDoctorsDAO
import com.armen.appointment.model.viewmodel.Screen

@Composable
fun HorizontalDivider() {
    Divider(color = Color.Gray)
}

@Preview
@Composable
private fun DoctorCardPreview() {
    val docDao = LocalDoctorsDAO()
    DoctorCard(doctor = docDao.getDoctors()[0])
}

@Composable
fun DoctorCard(
    id: Int? = 0,
    doctor: Doctor,
    navController: NavHostController? = null,
    drawButtons: Boolean = true
) {
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
                        modifier = Modifier.fillMaxWidth(0.82f)
                    ) {
                        Text(
                            text = doctor.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterStart),
                            maxLines = 1
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "heart",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(12.dp)
                        )
                        Text(
                            text = "${doctor.rating}%",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(width = 32.dp, height = 16.dp)
                                .padding(bottom = 2.dp),
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
                if (drawButtons) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                        ) {
                            Text("Timing", color = Color.Gray, fontSize = 10.sp)
                        }
                        OutlinedButton(
                            onClick = { navController?.navigate(Screen.Appointment.createRoute(id.toString())) },
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
}
