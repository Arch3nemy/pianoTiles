package com.alacrity.template.ui.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alacrity.template.ui.main.models.TileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.animation.core.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Tile(
    modifier: Modifier,
    translation: Float,
    isAnimated: Boolean,
    tileWidth: Int = 100,
    tileHeight: Int = 180,
    tileDisableDelay: Long = 3000,
    onTileNotPressed: () -> Unit,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var tileState by remember { mutableStateOf(TileState(Color.DarkGray, true)) }

    fun disableTileForDelay() {
        scope.launch {
            tileState = TileState(Color.White, false)
            delay(tileDisableDelay)
            tileState = TileState(Color.DarkGray, true)
        }
    }

    Box(
        modifier = modifier
            .size(width = tileWidth.dp, height = tileHeight.dp)
            .graphicsLayer {
                translationY = translation
                if (translation > 1900f && translation < 2000f) {
                    if (tileState.color == Color.DarkGray) {
                        tileState = TileState(Color.Red, false)
                        onTileNotPressed()
                    }
                }
            }
            .background(
                color = tileState.color,
                shape = RectangleShape
            )
            .clickable(enabled = isAnimated && tileState.enabled) {
                onClick()
                disableTileForDelay()
            }
    )



}

@Composable
fun RestartGameLabel(modifier: Modifier, onRestartGameClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier
                .size(65.dp)
                .align(Alignment.Center),
            onClick = { onRestartGameClick() }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "restart game button",
                    tint = Color.Red
                )
            }
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

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray), contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            circleValues.forEach { value ->
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .graphicsLayer {
                            translationY = -value * distance
                        }
                        .background(
                            color = circleColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}