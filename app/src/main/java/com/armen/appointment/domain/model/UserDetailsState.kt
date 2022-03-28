package com.armen.appointment.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

typealias TextState = MutableState<TextFieldValue>

class UserDetailsState(
    val name: TextState = mutableStateOf(TextFieldValue()),
    val phone: TextState = mutableStateOf(TextFieldValue()),
    val email: TextState = mutableStateOf(TextFieldValue()),
    val age: TextState = mutableStateOf(TextFieldValue()),
    val isMale: MutableState<Boolean> = mutableStateOf(true)
)