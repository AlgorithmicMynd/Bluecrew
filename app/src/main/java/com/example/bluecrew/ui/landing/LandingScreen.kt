package com.example.bluecrew.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bluecrew.R
import com.example.bluecrew.ui.splash.preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.landing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(60.dp))
                Text("BlueCrew", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
                Text(
                    "Restoring coasts, empowering communities,",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) { Text("Sign Up") }

                OutlinedButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 56.dp)
                ) { Text("Login") }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LandingPrev(){

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.landing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(60.dp))
                Text("BlueCrew", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
                Text(
                    "Restoring coasts, empowering communities,",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { /* TODO: Forgot Password */ },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) { Text("Sign Up") }

                OutlinedButton(
                    onClick = { /* TODO: Forgot Password */ },
                    modifier = Modifier.fillMaxWidth().padding( bottom = 56.dp)
                ) { Text("Login") }
            }
        }
    }
}
