package com.movieflick.ui.main


import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.movieflick.di.AppSettingsSharedPreference
import com.movieflick.ui.theme.AppTheme
import com.movieflick.ui.theme.MovieFlickTheme
import com.movieflick.ui.widget.NoInternetConnectionBanner
import dagger.hilt.android.AndroidEntryPoint
import utils.NetworkMonitor
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val DARK_MODE = "dark_mode"
    }

    @Inject
    @AppSettingsSharedPreference
    lateinit var appSettings: SharedPreferences

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private fun isDarkModeEnabled() = appSettings.getBoolean(DARK_MODE, false)

    private fun enableDarkMode(enable:Boolean) = appSettings.edit().putBoolean(DARK_MODE, enable).commit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()
            var darkMode by remember { mutableStateOf(isDarkModeEnabled()) }

            AppTheme(darkMode){
                Column {
                    val networkStatus by networkMonitor.networkState.collectAsState(null)

                    networkStatus?.let {
                        if(it.isOnline){
                            NoInternetConnectionBanner()
                        }
                    }

                    MainGraph(
                        mainNavController = navController,
                        darkMode = darkMode,
                        onThemeUpdated = {
                            val updated = !darkMode
                            enableDarkMode(updated)
                            darkMode = updated
                        }
                    )
                }
            }
        }

        /*setContent {
            MovieFlickTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieFlickTheme {
        Greeting("Android")
    }
}