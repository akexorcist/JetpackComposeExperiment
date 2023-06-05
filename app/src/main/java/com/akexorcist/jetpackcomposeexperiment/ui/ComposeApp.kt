package com.akexorcist.jetpackcomposeexperiment.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akexorcist.jetpackcomposeexperiment.ui.loading.FillMaxSizeLoading
import com.akexorcist.jetpackcomposeexperiment.ui.loading.LoadingHost
import com.akexorcist.jetpackcomposeexperiment.ui.loading.rememberLoadingHostState
import com.akexorcist.jetpackcomposeexperiment.ui.main.MainRoute
import com.akexorcist.jetpackcomposeexperiment.ui.menu.BottomMenuBar
import com.akexorcist.jetpackcomposeexperiment.ui.profile.ProfileRoute
import com.akexorcist.jetpackcomposeexperiment.ui.theme.JetpackComposeExperimentTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    val appNavigator = rememberAppNavigator(navController)
    val snackbarHostState = remember { SnackbarHostState() }
    val loadingHostState = rememberLoadingHostState()
    val composableScope = rememberCoroutineScope()

    JetpackComposeExperimentTheme {
        AppScaffold(
            loadingHost = {
                LoadingHost(
                    hostState = loadingHostState,
                    content = {
                        FillMaxSizeLoading()
                    },
                )
            },
            bottomBar = { BottomMenuBar(appNavigator = appNavigator) },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) {
            NavHost(
                navController = navController,
                startDestination = "main",
            ) {
                composable("main") {
                    MainRoute(
                        appNavigator = appNavigator,
                        performLoadingAction = {
                            composableScope.launch {
                                loadingHostState.perform(it)
                            }
                        },
                        showSnackbar = {
                            composableScope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        },
                    )
                }
                composable("profile") {
                    ProfileRoute(
                        navigateToMain = {
                            appNavigator.navigateToMain()
                        },
                        performLoadingAction = {
                            composableScope.launch {
                                loadingHostState.perform(it)
                            }
                        },
                        showSnackbar = {
                            composableScope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposeAppPreview() {
    ComposeApp()
}
