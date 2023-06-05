package com.akexorcist.jetpackcomposeexperiment.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FillMaxSizeLoading() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.75f),
                shape = RectangleShape,
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
        Spacer(
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Loading...",
            color = Color.White,
            fontSize = 28.sp
        )
    }
}

@Preview
@Composable
fun FillMaxSizeLoadingPreview() {
    FillMaxSizeLoading()
}
