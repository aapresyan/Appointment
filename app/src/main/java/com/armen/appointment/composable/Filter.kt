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
import com.armen.appointment.viewmodel.DoctorsViewModel
import com.armen.appointment.viewmodel.DoctorsViewModel.Companion.EXPERIENCE_RANGE
import com.armen.appointment.viewmodel.GenderFilter


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
                    { gender: GenderFilter -> viewModel?.genderFilter?.value == gender }
                val select = { gender: GenderFilter -> viewModel?.postGenderFilter(gender) }

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
                        selected = isSelected(GenderFilter.NONE),
                        onClick = { select(GenderFilter.NONE) },
                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "All",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    RadioButton(
                        selected = isSelected(GenderFilter.MALE),
                        onClick = { select(GenderFilter.MALE) },
                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "Male",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    RadioButton(
                        selected = isSelected(GenderFilter.FEMALE),
                        onClick = { select(GenderFilter.FEMALE) },
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
                    text = "Experience: ${viewModel?.experienceRangeFilter?.value?.start?.toInt()} - ${viewModel?.experienceRangeFilter?.value?.endInclusive?.toInt()}",
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
                        values = viewModel?.experienceRangeFilter?.value ?: EXPERIENCE_RANGE,
                        onValueChange = {
                            viewModel?.postAgeRange(it)
                        })

                }
            }
        }
    }
}

