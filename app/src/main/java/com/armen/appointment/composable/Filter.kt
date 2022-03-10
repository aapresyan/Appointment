@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.armen.appointment.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armen.appointment.model.Gender
import com.armen.appointment.model.viewmodel.DoctorsViewModel
import com.armen.appointment.model.viewmodel.DoctorsViewModel.Companion.EXPERIENCE_RANGE


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun Filter(viewModel: DoctorsViewModel? = null) {
    Card(
        shape = RoundedCornerShape(CornerSize(12.dp)), elevation = 8.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = Color.White
    ) {
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
                    RadioButton(
                        selected = isSelected(Gender.NONE),
                        onClick = { select(Gender.NONE) },
                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "All",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    RadioButton(
                        selected = isSelected(Gender.MALE),
                        onClick = { select(Gender.MALE) },
                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "Male",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    RadioButton(
                        selected = isSelected(Gender.FEMALE),
                        onClick = { select(Gender.FEMALE) },
                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "Female",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
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
                        .fillMaxWidth(0.8f)
                        .padding(start = 128.dp)
                ) {
                    RangeSlider(
                        valueRange = 1f..20f,
                        values = viewModel?.filter?.value?.range ?: EXPERIENCE_RANGE,
                        onValueChange = {
                            viewModel?.postAgeRange(it)
                        })

                }
            }
        }
    }
}

