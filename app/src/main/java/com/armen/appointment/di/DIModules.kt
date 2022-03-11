package com.armen.appointment.di

import com.armen.appointment.data.dao.DoctorsDao
import com.armen.appointment.data.dao.LocalDoctorsDAO
import com.armen.appointment.data.repository.DoctorsRepositoryImpl
import com.armen.appointment.domain.repository.DoctorsRepository
import com.armen.appointment.domain.usecase.DoctorsUseCase
import com.armen.appointment.domain.usecase.GetDoctors
import com.armen.appointment.presentation.doctors.DoctorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DIModules {

    val appModule = module {
        single<DoctorsDao> {
            LocalDoctorsDAO()
        }

        single<DoctorsRepository> {
            DoctorsRepositoryImpl(get())
        }

        single {
            GetDoctors(get())
        }

        single {
            DoctorsUseCase(get())
        }

        viewModel {
            DoctorsViewModel(get())
        }
    }

}