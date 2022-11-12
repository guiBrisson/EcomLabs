package me.brisson.ecomlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import me.brisson.ecomlabs.presentation.home.HomeScreen
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcomLabsTheme {
                HomeScreen()
            }
        }
    }
}
