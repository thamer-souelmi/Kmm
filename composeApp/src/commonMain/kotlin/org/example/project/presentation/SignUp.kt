package org.example.project.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.Home.HomeScreen
import org.example.project.presentation.login.GlassmorphicCard


class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF0A0F22), Color(0xFF1A2138))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF00E5FF)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Join our E-Bike community",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Signup Form
                GlassmorphicCard {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        UsernameField()
                        EmailField()
                        PasswordField()
                        ConfirmPasswordField()

                        GradientButton(
                            text = "Sign Up",
                            onClick = { navigator.push(HomeScreen()) }
                        )
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UsernameField() {
    val username = remember { mutableStateOf("") }

    OutlinedTextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text("Username", color = Color.White.copy(alpha = 0.8f)) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color(0xFF00E5FF),
            unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
            cursorColor = Color(0xFF00E5FF),
            focusedIndicatorColor = Color(0xFF00E5FF),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfirmPasswordField() {
    val confirmPassword = remember { mutableStateOf("") }

    OutlinedTextField(
        value = confirmPassword.value,
        onValueChange = { confirmPassword.value = it },
        label = { Text("Confirm Password", color = Color.White.copy(alpha = 0.8f)) },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color(0xFF00E5FF),
            unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
            cursorColor = Color(0xFF00E5FF),
            focusedIndicatorColor = Color(0xFF00E5FF),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailField() {
    val email = remember { mutableStateOf("") }

    OutlinedTextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = { Text("Email", color = Color.White.copy(alpha = 0.8f)) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color(0xFF00E5FF),
            unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
            cursorColor = Color(0xFF00E5FF),
            focusedIndicatorColor = Color(0xFF00E5FF),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField() {
    val password = remember { mutableStateOf("") }

    OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text("Password", color = Color.White.copy(alpha = 0.8f)) },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            // Same colors as EmailField
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color(0xFF00E5FF),
            unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
            cursorColor = Color(0xFF00E5FF),
            focusedIndicatorColor = Color(0xFF00E5FF),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

//@Composable
//fun GlassmorphicCard(content: @Composable () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(24.dp))
//            .background(Color(0x1AFFFFFF))
//            .border(
//                width = 1.dp,
//                brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color.White.copy(alpha = 0.2f),
//                        Color.Transparent
//                    )
//                ),
//                shape = RoundedCornerShape(24.dp)
//            )
//    ) {
//        content()
//    }
//}

@Composable
private fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush =
            Brush.horizontalGradient(
                colors = listOf(Color(0xFF00E5FF), Color(0xFF76FF03))
            )
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}
