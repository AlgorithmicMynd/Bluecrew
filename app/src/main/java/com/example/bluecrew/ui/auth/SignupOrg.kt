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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupOrg(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // New: dropdown menu state
    var expanded by remember { mutableStateOf(false) }
    val orgTypes = listOf("NGO", "Company")
    var selectedRole by remember { mutableStateOf(orgTypes[0]) } // default = NGO

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

        // Organisation Name
        Text("Organisation Name", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Your Organisation Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = registrationState !is RegistrationState.Loading
        )
        Spacer(Modifier.height(16.dp))

        // Email
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

        // Password
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

        // Confirm Password
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
        Spacer(Modifier.height(16.dp))

        // Organisation Type Dropdown
        Text("Organisation Type", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedRole,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                orgTypes.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(role) },
                        onClick = {
                            selectedRole = role
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(24.dp))

        // Sign Up Button / State Handling
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
                    LaunchedEffect(state.message) {
                        kotlinx.coroutines.delay(3000)
                        authViewModel.resetState()
                    }
                }
                is RegistrationState.Idle -> {
                    Button(
                        onClick = {
                            // Pass organisation type too
                            authViewModel.register(name, email, password, confirmPassword, selectedRole)
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

        Row(verticalAlignment = Alignment.CenterVertically) {
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

@Preview(showBackground = true)
@Composable
fun PreviewSignupOrg() {
    SignupOrg(navController = rememberNavController())
}
