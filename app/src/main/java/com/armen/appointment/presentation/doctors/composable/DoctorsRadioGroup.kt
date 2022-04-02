package com.armen.appointment.presentation.doctors.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.armen.appointment.presentation.Tab
import com.armen.appointment.presentation.doctors.DoctorsViewModel


@Composable
fun DoctorsRadioGroup(viewModel: DoctorsViewModel) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Tab.values().forEach { tab ->
            val isSelected = tab == viewModel.selectedTab.value
            OutlinedButton(
                onClick = { viewModel.postSelectedTab(tab) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .background(
                        Color.Transparent
                    )
                    .padding(4.dp), border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (isSelected)
                        Color.White else Color.Transparent
                )
            ) {
                Text(
                    text = tab.value,
                    color = if (isSelected)
                        MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant,
                )
            }
        }
    }
}