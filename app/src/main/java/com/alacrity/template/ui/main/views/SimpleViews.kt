package com.alacrity.template.ui.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Tile(modifier: Modifier, translation: Float, isAnimated: Boolean, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    var tileColor by remember { mutableStateOf(Color.DarkGray) }

    Box(
        modifier = modifier
            .size(width = 100.dp, height = 130.dp)
            .graphicsLayer {
                translationY = translation
            }
            .background(
                color = tileColor,
                shape = RectangleShape
            )
            .clickable(enabled = isAnimated) {
                onClick()
                scope.launch {
                    tileColor = Color.White
                    delay(2500)
                    tileColor = Color.DarkGray
                }
            }
    )
}

@Composable
fun RestartGameLabel(modifier: Modifier, onRestartGameClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { onRestartGameClick() },
        ) {
            Text(text = "Restart")
        }
    }
}

@Composable
fun PointsLabel(modifier: Modifier, points: Int) {
    Card(
        modifier = modifier
            .width(100.dp)
            .height(40.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 5.dp, top = 5.dp),
            text = points.toString(),
            textAlign = TextAlign.Center
        )
    }
}