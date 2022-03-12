package com.armen.appointment.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.armen.appointment.domain.model.Doctor

@Database(entities = [Doctor::class], version = 1)
abstract class DoctorsDatabase : RoomDatabase() {
    abstract val doctorsDao: DoctorsDao

    companion object {
        private const val DATABASE_NAME = "doctors_db"

        fun createDb(context: Context) =
            Room.databaseBuilder(
                context,
                DoctorsDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
