package com.akexorcist.jetpackcomposeexperiment.ui.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.akexorcist.jetpackcomposeexperiment.ui.AppNavigator

@Composable
fun BottomMenuBar(
    appNavigator: AppNavigator,
) {
    Row {
        Button(onClick = { appNavigator.navigateToMain() }) {
            Text(text = "Main", fontSize = 18.sp)
        }
        Button(onClick = { appNavigator.navigateToProfile() }) {
            Text(text = "Profile", fontSize = 18.sp)
        }
    }
}
