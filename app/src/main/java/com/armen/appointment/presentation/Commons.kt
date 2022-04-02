package com.armen.appointment.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
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
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun HorizontalDivider(trim: Dp = 0.dp) {
    Divider(
        color = MaterialTheme.colors.secondaryVariant,
        modifier = Modifier.padding(start = trim, end = trim)
    )
}

@Composable
fun DefaultCard(content: @Composable () -> Unit) =
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(CornerSize(12.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.background
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
            backgroundColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier,
        trailingIcon = {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    )

@Composable
fun Option(text: String, isChecked: Boolean, onChecked: () -> Unit) =
    Row {
        RadioButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            selected = isChecked,
            colors = RadioButtonDefaults.colors(
                unselectedColor = Color.Gray,
                selectedColor = MaterialTheme.colors.primary
            ),
            onClick = onChecked
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ) { onChecked() },
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }

