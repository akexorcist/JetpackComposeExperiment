package com.akexorcist.jetpackcomposeexperiment.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.akexorcist.jetpackcomposeexperiment.ui.AppNavigator
import com.akexorcist.jetpackcomposeexperiment.ui.loading.LoadingAction
import kotlinx.coroutines.delay

@Composable
fun MainRoute(
    appNavigator: AppNavigator,
    performLoadingAction: (LoadingAction) -> Unit,
    showSnackbar: (message: String) -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(1000L)
        performLoadingAction(LoadingAction.Dismiss)
    }
    MainScreen(
        navigateToProfile = { appNavigator.navigateToProfile() },
        performLoadingAction = performLoadingAction,
        showSnackbar = showSnackbar,
    )
}
