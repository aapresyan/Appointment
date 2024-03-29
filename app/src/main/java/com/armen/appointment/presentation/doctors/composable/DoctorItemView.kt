package com.armen.appointment.presentation.doctors.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.armen.appointment.R
import com.armen.appointment.domain.model.Doctor
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.HorizontalDivider
import com.armen.appointment.presentation.Screen
import com.armen.appointment.ui.theme.PrimaryBlue

@Composable
fun DoctorCard(
    doctor: Doctor,
    navController: NavHostController? = null,
    drawButtons: Boolean = true,
    showTimings: (() -> Unit)? = null
) {
    DefaultCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            if (doctor.drawableId != 0) {
                Image(
                    painter = painterResource(id = doctor.drawableId),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                )
            }
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
                                .padding(top = 2.5.dp)
                                .size(12.dp)
                        )
                        Text(
                            text = "${doctor.rating}%",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(width = 32.dp, height = 16.dp),
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
                            onClick = {
                                showTimings?.invoke()
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.background),
                            border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant)
                        ) {
                            Text("Timing", color = MaterialTheme.colors.secondaryVariant, fontSize = 10.sp)
                        }
                        OutlinedButton(
                            onClick = {
                                navController?.navigate(
                                    Screen.Appointment.createRoute(
                                        doctor.id.toString()
                                    )
                                )
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = PrimaryBlue)
                        ) {
                            Text("Book Appointment", color = Color.White, fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }
}
