package org.example.project.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.Home.HomeScreen
import org.example.project.presentation.Login.LoginIntent
import org.example.project.presentation.Login.LoginState
import org.example.project.presentation.Login.LoginViewModel
import org.example.project.presentation.SignUpScreen
import org.example.project.theme.AppTheme
import org.koin.compose.koinInject

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: LoginViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        AppTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                when (state) {
                    is LoginState.Loading -> FullScreenLoader()
                    is LoginState.Error -> ErrorMessage((state as LoginState.Error).message)
                    else -> LoginContent(
                        email = email,
                        password = password,
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it },
                        onLogin = { viewModel.handleIntent(LoginIntent.Login(email, password)) },
                        navigator = navigator
                    )
                }

                // Handle successful login
                LaunchedEffect(state) {
                    if (state is LoginState.Success) {
                        navigator.push(HomeScreen())
                    }
                }
            }
        }
    }

    @Composable
    private fun LoginContent(
        email: String,
        password: String,
        onEmailChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        onLogin: () -> Unit,
        navigator: Navigator
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome Back",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Sign in to continue",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            GlassmorphicCard {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    EmailField(
                        value = email,
                        onValueChange = onEmailChange
                    )

                    PasswordField(
                        value = password,
                        onValueChange = onPasswordChange
                    )

                    GradientButton(
                        text = "Login",
                        onClick = onLogin
                    )

                    GradientButton(
                        text = "Sign Up",
                        onClick = { navigator.push(SignUpScreen()) }
                    )
                }
            }
        }
    }

    @Composable
    private fun FullScreenLoader() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

    @Composable
    private fun ErrorMessage(message: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    @Composable
    private fun EmailField(value: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    private fun PasswordField(value: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun GlassmorphicCard(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0x1AFFFFFF))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        content()
    }
}

// components/GradientButton.kt
@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        border = BorderStroke(
            2.dp,
            Brush.horizontalGradient(
                colors = listOf(Color(0xFF00E5FF), Color(0xFF76FF03))
            )
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}