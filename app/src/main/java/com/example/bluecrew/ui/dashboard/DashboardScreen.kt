package com.example.bluecrew.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Projects") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Approved: 2")
            Text("Pending: 3")
            Text("Rejected: 1")

            Spacer(Modifier.height(24.dp))

            Button(onClick = { /* navigate to profile later */ }) {
                Text("Profile Settings")
            }
        }
    }
}
