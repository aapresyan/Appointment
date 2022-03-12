package com.armen.appointment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
    )
    HorizontalDivider()
}

@Composable
fun HorizontalDivider() {
    Divider(color = Color.Gray)
}

fun Modifier.mainThemeModifier() =
    this
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                listOf(
                    Color(0xFF4C4CF5),
                    Color(0xFF8C66F3)
                )
            )
        )
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
