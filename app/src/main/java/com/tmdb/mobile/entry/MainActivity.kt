package com.tmdb.mobile.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tmdb.designsystem.components.AppAlertDialog
import com.tmdb.designsystem.theme.MovieQuestTheme
import com.tmdb.discover.ui.Discover
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.mobile.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var uiState: MainUIState by mutableStateOf(MainUIState())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collectLatest { uiState = it }
            }
        }
        initUI()
    }

    private fun initUI() {
        setContent {
            val darkTheme = shouldUseDarkTheme(uiState)
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.YELLOW,
                        android.graphics.Color.YELLOW,
                    ) { darkTheme },
                )
                onDispose {}
            }

            val navController: NavHostController = rememberNavController()
            NavGraph(
                startDestination = Discover,
                navController = navController,
                darkTheme = darkTheme,
                dynamicColor = shouldDisableDynamicTheming(uiState),
            ) {
                HandleUserMessage(viewModel = viewModel)
            }
        }
    }

    @Composable
    private fun HandleUserMessage(viewModel: MainViewModel) {
        var userMessage by remember { mutableStateOf<String?>(null) }
        val event by viewModel.event.collectAsState(initial = null)

        LaunchedEffect(event) {
            when (event) {
                is MainEvent.UserMessage -> {
                    userMessage = (event as MainEvent.UserMessage).message
                }

                else -> {}
            }
        }

        if (userMessage != null) {
            AppAlertDialog(
                body = userMessage!!,
                confirmButton = {
                    userMessage = null
                    viewModel.userMessageShown()
                }
            )
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainUIState,
): Boolean = when (uiState.settings.darkThemeConfig) {
    DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
    DarkThemeConfig.LIGHT -> false
    DarkThemeConfig.DARK -> true
}


@Composable
private fun shouldDisableDynamicTheming(
    uiState: MainUIState,
): Boolean = uiState.settings.useDynamicColor


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
    MovieQuestTheme {
        Greeting("Android")
    }
}