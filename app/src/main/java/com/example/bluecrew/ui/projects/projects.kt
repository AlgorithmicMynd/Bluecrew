package com.example.bluecrew.ui.projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.AsyncImage

// Data class for a single project
data class Project(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val location: String,
    val area: String,
    val carbonCredits: String,
    val status: ProjectStatus
)

// Enum for project status
enum class ProjectStatus(val color: Color) {
    Approved(Color(0xFF388E3C)), // Dark Green
    Pending(Color(0xFFFF9800)),  // Orange
    Rejected(Color(0xFFD32F2F))  // Red
}

// Sample data for projects
val projects = listOf(
    Project(
        id = 1,
        title = "Sundarbans Mangrove Restoration",
        imageUrl = "https://picsum.photos/id/1018/1000/600",
        location = "West Bengal, India",
        area = "5.2 sq km",
        carbonCredits = "1250",
        status = ProjectStatus.Approved
    ),
    Project(
        id = 2,
        title = "Coastal Dune Stabilization Project",
        imageUrl = "https://picsum.photos/id/1015/1000/600",
        location = "Kerala, India",
        area = "2.1 sq km",
        carbonCredits = "480",
        status = ProjectStatus.Pending
    ),
    Project(
        id = 3,
        title = "Seagrass Meadow Rehabilitation",
        imageUrl = "https://picsum.photos/id/1016/1000/600",
        location = "Tamil Nadu, India",
        area = "1.8 sq km",
        carbonCredits = "320",
        status = ProjectStatus.Rejected
    ),
    Project(
        id = 4,
        title = "Coral Reef Conservation Initiative",
        imageUrl = "https://picsum.photos/id/1019/1000/600",
        location = "Andaman & Nicobar, India",
        area = "3.5 sq km",
        carbonCredits = "890",
        status = ProjectStatus.Approved
    ),
    Project(
        id = 5,
        title = "Wetland Biodiversity Enhancement",
        imageUrl = "https://picsum.photos/id/1020/1000/600",
        location = "Assam, India",
        area = "4.0 sq km",
        carbonCredits = "1020",
        status = ProjectStatus.Pending
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Projects",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Dashboard
                    IconButton(onClick = { navController.navigate("dashboard") }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Dashboard",
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Dashboard", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    // Projects
                    IconButton(onClick = { navController.navigate("projects") }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "Projects",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Projects", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    // Credits
                    IconButton(onClick = { navController.navigate("credits") }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = "Credits",
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Credits", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    // Settings
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Settings", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_project") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Project", tint = Color.White)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF0F0F0)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            items(projects) { project ->
                ProjectCard(project = project)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Project Image
            AsyncImage(
                model = project.imageUrl,
                contentDescription = project.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            // Project Details
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProjectInfoRow(
                    icon = Icons.Default.LocationOn,
                    text = "Location: ${project.location}"
                )
                ProjectInfoRow(
                    icon = Icons.Default.FilterFrames,
                    text = "Area Restored: ${project.area}"
                )
                ProjectInfoRow(
                    icon = Icons.Default.AccountTree,
                    text = "Carbon Credits: ${project.carbonCredits} Blue Carbon Credits"
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Status Chip
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = project.status.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = project.status.color
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectInfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsScreen() {
    ProjectsScreen(navController = rememberNavController())
}
