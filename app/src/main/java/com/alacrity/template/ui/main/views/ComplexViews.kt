package com.alacrity.template.ui.main.views

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.alacrity.template.ui.main.models.GameState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    spaceBetween: Dp = 0.dp,
    travelDistance: Dp = 1500.dp,
    gameState: GameState,
    onTileClick: () -> Unit,
    onNonTileClick: () -> Unit,
    onRestartGameClick: () -> Unit
) {

    val tiles = mutableListOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    var isGameEndable by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(3000)
        isGameEndable = true
    }

    LaunchedEffect(key1 = gameState) {
        if (!gameState.isAnimated) {
            tiles.forEach { animatable ->
                animatable.stop()
            }
        }
    }

    tiles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            animateTiles(index, animatable)
        }
    }

    val circleValues = tiles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .clickable(enabled = isGameEndable) {
            onNonTileClick()
        }) {
        if (!gameState.isAnimated) RestartGameLabel(modifier = Modifier.align(Center)) {
            onRestartGameClick()
        }
        Column(modifier = Modifier.wrapContentSize()) {
            PointsLabel(modifier = Modifier.align(CenterHorizontally), points = gameState.points)
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
            Row(
                modifier = modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(spaceBetween)
            ) {
                circleValues.forEach { value ->
                    Tile(
                        modifier = Modifier.align(Top),
                        translation = value * distance,
                        isAnimated = gameState.isAnimated
                    ) {
                        onTileClick()
                    }
                }
            }
        }

    }
}

private suspend fun animateTiles(index: Int, animatable: Animatable<Float, AnimationVector1D>) {
    delay(index * 1000L)
    animatable.animateTo(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 6000
            },
            repeatMode = RepeatMode.Restart
        )
    )
}

