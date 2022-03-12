package com.armen.appointment.data.dao

import androidx.room.*
import com.armen.appointment.domain.model.Doctor
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorsDao {

    @Query("select * from doctor")
    fun getDoctors(): Flow<List<Doctor>>

    @Query("select * from doctor where id = :id")
    suspend fun getDoctorById(id: Int): Doctor?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDoctor(doctor: Doctor)

    @Insert
    suspend fun updateDoctors(doctors: List<Doctor>)

}