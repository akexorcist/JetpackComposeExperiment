package com.akexorcist.jetpackcomposeexperiment.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akexorcist.jetpackcomposeexperiment.ui.loading.LoadingAction

@Composable
fun MainScreen(
    navigateToProfile: () -> Unit,
    performLoadingAction: (LoadingAction) -> Unit,
    showSnackbar: (message: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Main",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    performLoadingAction(LoadingAction.Show)
                    navigateToProfile()
                }) {
                Text(text = "Go to profile with loading")
            }
            Button(
                onClick = {
                    showSnackbar("Loading...")
                    navigateToProfile()
                }) {
                Text(text = "Go to profile with snackbar")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navigateToProfile = {},
        performLoadingAction = {},
        showSnackbar = {},
    )
}
