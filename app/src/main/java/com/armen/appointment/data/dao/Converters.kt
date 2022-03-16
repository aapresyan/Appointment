package com.armen.appointment.data.dao

import androidx.room.TypeConverter
import com.armen.appointment.domain.model.TimeSlots
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun timeSlotsToJsonString(value: TimeSlots?): String =
        Gson().toJson(listOf(value?.morningTimes, value?.afternoonTimes, value?.eveningTimes))

    @TypeConverter
    fun jsonStringToTimeSlots(value: String): TimeSlots {
        Gson().fromJson(value, Array<String>::class.java).toList()
    }

}