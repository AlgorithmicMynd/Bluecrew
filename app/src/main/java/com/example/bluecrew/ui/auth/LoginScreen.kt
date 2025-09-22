package com.example.bluecrew.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Listen to the login state from the ViewModel
    val loginState by authViewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        val logoPainter: Painter = painterResource(id = R.drawable.logo)
        Image(
            painter = logoPainter,
            contentDescription = "Blue Crew Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(Modifier.height(16.dp))

        Text("Welcome Back", style = MaterialTheme.typography.titleLarge)
        Text("Login to your account", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("email@example.com") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = loginState !is LoginState.Loading
        )

        Spacer(Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("anything") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = loginState !is LoginState.Loading
        )

        Spacer(Modifier.height(32.dp))

        // Box for Button, Loader, and Error Message
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val state = loginState) {
                is LoginState.Loading -> {
                    CircularProgressIndicator()
                }
                is LoginState.Success -> {
                    // Navigate to dashboard on success
                    LaunchedEffect(Unit) {
                        navController.navigate("dashboard") {
                            // Clear the whole back stack, so user can't press 'back' to get to Login
                            popUpTo(0)
                        }
                    }
                }
                is LoginState.Error -> {
                    Text(text = state.message, color = MaterialTheme.colorScheme.error)
                    // Reset state after a few seconds so the user can try again
                    LaunchedEffect(state.message) {
                        kotlinx.coroutines.delay(3000)
                        authViewModel.resetLoginState()
                    }
                }
                is LoginState.Idle -> {
                    Button(
                        onClick = { authViewModel.login(email, password) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Login")
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?", style = MaterialTheme.typography.bodyMedium)
            TextButton(
                onClick = { navController.navigate("register") },
                enabled = loginState !is LoginState.Loading
            ) {
                Text("Sign Up")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}
