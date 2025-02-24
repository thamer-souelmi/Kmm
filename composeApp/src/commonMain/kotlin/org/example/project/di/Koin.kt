package org.example.project.di

import org.example.project.data.repository.AuthRepositoryImpl
import org.example.project.domain.repository.AuthRepository
import org.example.project.domain.use_case.LoginUseCase
import org.example.project.presentation.Login.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

val appModule = module {
    // Repository
    single<AuthRepository> { AuthRepositoryImpl() }

    // Use Cases
    factory { LoginUseCase(get()) }

    // ViewModels
//    viewModel { LoginViewModel(get()) }
}