package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.project.di.initKoin
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.example.project.presentation.Home.HomeScreen
import org.example.project.presentation.SignUpScreen
import org.example.project.presentation.login.LoginScreen

@Composable
@Preview
fun App() {
    initKoin() // Initialize Koin here

    Navigator(LoginScreen()) { navigator ->
        MaterialTheme {
            SlideTransition(navigator) { screen ->
                when (screen) {
                    is LoginScreen -> screen.Content()
                    is SignUpScreen -> screen.Content()
                    is HomeScreen -> screen.Content()
                }
            }
        }
    }
}