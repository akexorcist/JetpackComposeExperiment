package com.akexorcist.jetpackcomposeexperiment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController

class AppNavigator(private val navController: NavController) {
    fun navigateToMain() {
        navController.navigate(
            route = "main",
            builder = {
                popUpTo("main")
                launchSingleTop = true
            },
        )
    }

    fun navigateToProfile() {
        navController.navigate(
            route = "profile",
            builder = {
                popUpTo("profile")
                launchSingleTop = true
            },
        )
    }
}


@Composable
fun rememberAppNavigator(
    navController: NavController
): AppNavigator {
    return remember(navController) {
        AppNavigator(navController)
    }
}
