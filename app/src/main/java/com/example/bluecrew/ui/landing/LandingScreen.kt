//package com.example.bluecrew.ui.landing
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
//import com.example.bluecrew.R
//import com.example.bluecrew.ui.splash.preview
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LandingScreen(navController: NavController) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = rememberAsyncImagePainter(R.drawable.landing),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Spacer(Modifier.height(60.dp))
//                Text("BlueCrew", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
//                Text(
//                    "Restoring coasts, empowering communities,",
//                    style = MaterialTheme.typography.bodyMedium,
//                    textAlign = TextAlign.Center
//                )
//            }
//
//            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Button(
//                    onClick = { navController.navigate("register") },
//                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                ) { Text("Sign Up") }
//
//                OutlinedButton(
//                    onClick = { navController.navigate("login") },
//                    modifier = Modifier.fillMaxWidth().padding(bottom = 56.dp)
//                ) { Text("Login") }
//            }
//        }
//    }
//}
//
//@Preview (showBackground = true)
//@Composable
//fun LandingPrev(){
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = rememberAsyncImagePainter(R.drawable.landing),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Spacer(Modifier.height(60.dp))
//                Text("BlueCrew", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
//                Text(
//                    "Restoring coasts, empowering communities,",
//                    style = MaterialTheme.typography.bodyMedium,
//                    textAlign = TextAlign.Center
//                )
//            }
//
//            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Button(
//                    onClick = { /* TODO: Forgot Password */ },
//                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                ) { Text("Sign Up") }
//
//                OutlinedButton(
//                    onClick = { /* TODO: Forgot Password */ },
//                    modifier = Modifier.fillMaxWidth().padding( bottom = 56.dp)
//                ) { Text("Login") }
//            }
//        }
//    }
//}
package com.example.bluecrew.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.landing),
            contentDescription = "Landing Page Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "BlueCrew",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    "Restoring coasts, empowering communities",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("SignupOrg") },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Sign Up as Organisation", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(Modifier.height(16.dp))
                Button(
                        onClick = { navController.navigate("SignupUser") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
                ) {
                Text("Sign Up as Individual", style = MaterialTheme.typography.titleMedium)
            }

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text("Login", style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPreview() {
    LandingScreen(navController = rememberNavController())
}
