package com.armen.appointment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.ui.theme.PrimaryBlue
import com.armen.appointment.ui.theme.SecondaryBlue

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
    Spacer(modifier = Modifier.height(8.dp))
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
                    PrimaryBlue,
                    SecondaryBlue
                )
            )
        )
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)

@Composable
fun DefaultCard(content: @Composable () -> Unit) =
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(CornerSize(12.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = Color.White
    ) {
        content()
    }

