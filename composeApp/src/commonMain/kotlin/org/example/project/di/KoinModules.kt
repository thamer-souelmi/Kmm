package org.example.project.di

import org.example.project.data.repository.AuthRepositoryImpl
import org.example.project.domain.repository.AuthRepository
import org.example.project.domain.use_case.LoginUseCase
import org.koin.dsl.module

//val appModule = module {
//    single<AuthRepository> { AuthRepositoryImpl() }
//    factory { LoginUseCase(get()) }
//}