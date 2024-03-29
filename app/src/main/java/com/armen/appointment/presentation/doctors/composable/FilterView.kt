@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.armen.appointment.presentation.doctors.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.presentation.Constants.Companion.DEFAULT_EXPERIENCE_RANGE
import com.armen.appointment.presentation.DefaultCard
import com.armen.appointment.presentation.HorizontalDivider
import com.armen.appointment.presentation.Option
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import com.armen.appointment.presentation.doctors.Gender


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Filter(viewModel: DoctorsViewModel? = null) {
    DefaultCard {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                val isSelected =
                    { gender: Gender -> viewModel?.filter?.value?.gender == gender }
                val select = { gender: Gender -> viewModel?.postGenderFilter(gender) }

                Text(
                    text = "Gender: ",
                    modifier = Modifier.align(Alignment.CenterStart),
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .padding(start = 48.dp)
                ) {
                    Option(text = "All", isChecked = isSelected(Gender.NONE)) {
                        select(Gender.NONE)
                    }
                    Option(text = "Male", isChecked = isSelected(Gender.MALE)) {
                        select(Gender.MALE)
                    }
                    Option(text = "Female", isChecked = isSelected(Gender.FEMALE)) {
                        select(Gender.FEMALE)
                    }
                }
            }
            HorizontalDivider()
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = "Experience: ${viewModel?.filter?.value?.range?.start?.toInt()} - ${viewModel?.filter?.value?.range?.endInclusive?.toInt()}",
                    modifier = Modifier.align(Alignment.CenterStart),
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .padding(start = 128.dp)
                ) {
                    RangeSlider(
                        valueRange = DEFAULT_EXPERIENCE_RANGE,
                        values = viewModel?.filter?.value?.range ?: DEFAULT_EXPERIENCE_RANGE,
                        onValueChange = {
                            viewModel?.postAgeRange(it)
                        })

                }
            }
        }
    }
}

