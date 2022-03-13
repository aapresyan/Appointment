package com.armen.appointment.di

import com.armen.appointment.data.dao.DoctorsDatabase
import com.armen.appointment.data.repository.DoctorsRepositoryImpl
import com.armen.appointment.domain.repository.DoctorsRepository
import com.armen.appointment.domain.usecase.*
import com.armen.appointment.presentation.appointment.AppointmentViewModel
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DIModule {

    val appModule = module {

        single {
            DoctorsDatabase.createDb(androidApplication())
        }

        single<DoctorsRepository> {
            DoctorsRepositoryImpl(get<DoctorsDatabase>().doctorsDao)
        }

        single {
            DoctorsUseCase(
                GetDoctors(get()),
                GetDoctor((get())),
                UpdateDoctor(get()),
                UpdateDoctors(get())
            )
        }

        viewModel {
            DoctorsViewModel(get())
        }

        viewModel {
            AppointmentViewModel(get())
        }
    }

}