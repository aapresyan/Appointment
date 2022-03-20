package com.armen.appointment.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue

typealias TextState = MutableState<TextFieldValue>

class UserDetailsState(
    val name: TextState,
    val phone: TextState,
    val email: TextState,
    val age: TextState,
    val isMale: MutableState<Boolean>
)