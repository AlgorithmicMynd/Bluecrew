package com.example.bluecrew.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R

// Assuming you have a drawable for the logo, e.g., R.drawable.blue_crew_logo
// and the following imports are correctly set up.
// You will need to add the Font Awesome or other icon library dependency if you want
// to use the role icons.
// For demonstration, we'll use a placeholder R.drawable.blue_crew_logo
@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("NGO") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))

        // Logo
        // Replace with your actual logo painter
        val logoPainter: Painter = painterResource(id = R.drawable.logo)
        Image(
            painter = logoPainter,
            contentDescription = "Blue Crew Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

//        Spacer(Modifier.height(16.dp))

        Text(
            "Create Your Account",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(10.dp))

        // Name / Organization Field
        Text("Name / Organization", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Your Name or Organization") },
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Role Selection
        Text("Your Role", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // To use your own icons, place them in the res/drawable folder
            // and replace the painterResource calls below with your icon names.
            RoleChip(
                text = "NGO",
                icon = painterResource(id = R.drawable.ngo), // Replace with your icon
                isSelected = selectedRole == "NGO",
                onClick = { selectedRole = "NGO" }
            )
            RoleChip(
                text = "Community",
                icon = painterResource(id = R.drawable.user), // Replace with your icon
                isSelected = selectedRole == "Community",
                onClick = { selectedRole = "Community" }
            )
            RoleChip(
                text = "Organisation",
                icon = painterResource(id = R.drawable.panchayat), // Replace with your icon
                isSelected = selectedRole == "Organisation",
                onClick = { selectedRole = "Organisation" }
            )
        }

        Spacer(Modifier.height(16.dp))

        // Email Field
        Text("Email", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("email@example.com") },
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Password Field
        Text("Password", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Confirm Password Field
        Text("Confirm Password", style = MaterialTheme.typography.labelLarge, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("dashboard") },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign Up")
        }
//
//        Spacer(Modifier.height(5.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", style = MaterialTheme.typography.bodyMedium)
            TextButton(onClick = { navController.navigate("login") }) {
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
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}

