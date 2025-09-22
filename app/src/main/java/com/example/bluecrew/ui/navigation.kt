package com.example.bluecrew.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.ui.splash.SplashScreen
import com.example.bluecrew.ui.auth.LoginScreen
import com.example.bluecrew.ui.auth.SignupUser
import com.example.bluecrew.ui.auth.SignupOrg
import com.example.bluecrew.ui.settings.SettingsScreen
import com.example.bluecrew.ui.dashboard.DashboardScreen
import com.example.bluecrew.ui.landing.LandingScreen
import com.example.bluecrew.ui.credits.CreditsScreen
import com.example.bluecrew.ui.add_projects.AddProjectScreen
import com.example.bluecrew.ui.projects.ProjectsScreen

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("landing") {
            LandingScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("SignupUser") {
            SignupUser(navController = navController)
        }
        composable("SignupOrg") {
            SignupOrg(navController = navController)
        }
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }
        composable("projects") {
            ProjectsScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("credits") {
            CreditsScreen(navController = navController)
        }
        composable("add_project") {
            AddProjectScreen(navController = navController)
        }
    }
}
