package com.akexorcist.jetpackcomposeexperiment.ui.profile

import androidx.compose.runtime.Composable
import com.akexorcist.jetpackcomposeexperiment.ui.loading.LoadingAction

@Composable
fun ProfileRoute(
    navigateToMain: () -> Unit,
    performLoadingAction: (LoadingAction) -> Unit,
    showSnackbar: (message: String) -> Unit,
) {
    ProfileScreen(
        navigateToMain = navigateToMain,
        performLoadingAction = performLoadingAction,
        showSnackbar = showSnackbar,
    )
}
