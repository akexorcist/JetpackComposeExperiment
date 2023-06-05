package com.akexorcist.jetpackcomposeexperiment.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akexorcist.jetpackcomposeexperiment.ui.loading.LoadingAction
import kotlinx.coroutines.delay


@Composable
fun ProfileScreen(
    navigateToMain: () -> Unit,
    performLoadingAction: (LoadingAction) -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1000L)
        performLoadingAction(LoadingAction.Dismiss)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Profile",
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
                    navigateToMain()
                }) {
                Text(text = "Go to main with loading")
            }
            Button(
                onClick = {
                    showSnackbar("Loading...",)
                    navigateToMain()
                }) {
                Text(text = "Go to main with snackbar")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navigateToMain = {},
        performLoadingAction = {},
        showSnackbar = {},
    )
}
