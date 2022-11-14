package me.brisson.ecomlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.util.ConnectionUtils
import me.brisson.ecomlabs.util.NoInternetConnectionComponent

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcomLabsTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    if(!ConnectionUtils.isOnline(LocalContext.current)){
                        NoInternetConnectionComponent()
                    }
                    AppNavGraph()
                }
            }
        }
    }
}
