package com.example.bluecrew.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.ui.auth.LoginScreen
import com.example.bluecrew.ui.auth.RegisterScreen
import com.example.bluecrew.ui.dashboard.DashboardScreen
import com.example.bluecrew.ui.landing.LandingScreen
import com.example.bluecrew.ui.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("landing") { LandingScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
    }
}
