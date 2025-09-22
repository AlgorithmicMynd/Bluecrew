package com.example.bluecrew.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R


@Composable
fun SignupUser(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("NGO") }

    // Collect the registration state from the ViewModel
    val registrationState by authViewModel.registrationState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))

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

        Text(
            "Create Your Account",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(10.dp))

        // Name / Organization Field
        Text("Name", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Your Name ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = registrationState !is RegistrationState.Loading
        )

        Spacer(Modifier.height(16.dp))


        // Email Field
        Text("Email", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("email@example.com") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = registrationState !is RegistrationState.Loading
        )

        Spacer(Modifier.height(16.dp))

        // Password Field
        Text("Password", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = registrationState !is RegistrationState.Loading
        )

        Spacer(Modifier.height(16.dp))

        // Confirm Password Field
        Text("Confirm Password", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = registrationState !is RegistrationState.Loading
        )

        Spacer(Modifier.height(24.dp))

        // This Box will manage showing the button, loading spinner, or error message
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = registrationState) {
                is RegistrationState.Loading -> {
                    CircularProgressIndicator()
                }
                is RegistrationState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
                is RegistrationState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    // Reset state after a delay so the user can try again
                    LaunchedEffect(state.message) {
                        kotlinx.coroutines.delay(3000)
                        authViewModel.resetState()
                    }
                }
                is RegistrationState.Idle -> {
                    Button(
                        onClick = {
                            authViewModel.register(name, email, password, confirmPassword)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Sign Up")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", style = MaterialTheme.typography.bodyMedium)
            TextButton(
                onClick = { navController.navigate("login") },
                enabled = registrationState !is RegistrationState.Loading
            ) {
                Text("Login")
            }
        }
    }
}

@Composable
fun RoleChip(text: String, icon: Painter, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent

    Column(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                onClick = onClick,
            )
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .width(88.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.height(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupUser() {
    SignupUser(navController = rememberNavController())
}