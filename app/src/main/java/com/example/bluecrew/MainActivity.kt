package com.example.bluecrew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluecrew.ui.theme.BluecrewTheme


import androidx.activity.compose.setContent
import com.example.bluecrew.navigation.AppNavHost
import com.example.bluecrew.ui.theme.BluecrewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BluecrewTheme {
                AppNavHost()
            }
        }
    }
}
