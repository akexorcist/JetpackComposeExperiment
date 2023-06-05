package com.akexorcist.jetpackcomposeexperiment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNiaAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState {
    return remember(
        coroutineScope,
        navController,
    ) {
        AppState(
            coroutineScope = coroutineScope,
            navController = navController,
        )
    }
}


class AppState(
    val coroutineScope: CoroutineScope,
    val navController: NavHostController,
//    val showFullScreenLoading: Boolean,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

//    fun showLoading() {
//        showFullScreenLoading = true
//    }
//    fun showLoading() {
//        showFullScreenLoading = true
//    }
}
