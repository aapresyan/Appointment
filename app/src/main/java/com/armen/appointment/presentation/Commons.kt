package com.armen.appointment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
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
fun HorizontalDivider(trim: Dp = 0.dp) {
    Divider(color = Color.Gray, modifier = Modifier.padding(start = trim, end = trim))
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

@Composable
fun TextFieldWithHint(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector? = null
) =
    TextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(text = hint) },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier,
        trailingIcon = { icon?.let { Icon(imageVector = it, contentDescription = null, tint = Color.Gray) } }
    )

@Composable
fun Option(text: String, isChecked: Boolean, onChecked: () -> Unit) =
    Row {
        RadioButton(modifier = Modifier
            .align(Alignment.CenterVertically),
            selected = isChecked,
            colors = RadioButtonDefaults.colors(
                unselectedColor = Color.Gray,
                selectedColor = PrimaryBlue
            ),
            onClick = onChecked)
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onChecked() },
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }

