package com.alacrity.pianoTiles.ui.main.views

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alacrity.pianoTiles.theme.DefaultBackground
import com.alacrity.pianoTiles.ui.main.models.GameState
import kotlinx.coroutines.delay
import timber.log.Timber


@Composable
fun AnimatedTiles(
    modifier: Modifier = Modifier,
    spaceBetween: Dp = 0.dp,
    travelDistance: Dp = 1500.dp,
    gameState: GameState,
    onTileClick: () -> Unit,
    onGameLost: () -> Unit,
    onRestartGameClick: () -> Unit,
    onBackToMenuClick: () -> Unit
) {

    var isGameEndable by remember { mutableStateOf(false) }
    val tiles = mutableListOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    LaunchedEffect(key1 = Unit) {
        delay(3000)
        isGameEndable = true
    }

    tiles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = gameState) {
            Timber.d("gameState key, $gameState")
            if (!gameState.isAnimated) {
                animatable.stop()
            } else {
                /* if (gameState.points in listOf(400, 800, 1200, 1600, 2000)) {
                     animatable.stop()
                     animateTiles(
                         index = index,
                         animatable = animatable,
                         duration = duration
                     )

                 }*/ //TODO try side effect (happens after recomposition completes)
            }
        }
    }

    tiles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            Timber.d("Animate Tiles first time")
            animateTiles(index = index, animatable = animatable, duration = 5200)
        }
    }


    val circleValues = tiles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    var accelerationIndex by remember { mutableStateOf(1f) }


    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(DefaultBackground)
        .clickable(enabled = isGameEndable && gameState.isAnimated) {
            onGameLost()
        }) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight().background(Color.Black))
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight().background(Color.Black))
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight().background(Color.Black))
        }
        if (!gameState.isAnimated) {
            EndGameMenu(
                modifier = Modifier.zIndex(2f),
                points = gameState.points,
                onRestartGameClick = { onRestartGameClick() },
                onBackToMenuClick = { onBackToMenuClick() }
            )
        }

        Column(modifier = Modifier.wrapContentSize()) {
            if (gameState.isAnimated) {
                PointsLabel(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 20.dp)
                        .zIndex(2f), points = gameState.points
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Row(
                modifier = modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(spaceBetween)
            ) {
                circleValues.forEach { value ->
                    Tile(
                        modifier = Modifier.align(Top).weight(1f),
                        translation = value * distance * accelerationIndex,
                        isAnimated = gameState.isAnimated,
                        onTileNotPressed = { onGameLost() }
                    ) {
                        onTileClick()
                        accelerationIndex += 0.04f
                    }
                }
            }
        }
    }
}


private suspend fun animateTiles(
    targetValue: Float = 1f,
    withDelay: Boolean = true,
    index: Int,
    animatable: Animatable<Float, AnimationVector1D>,
    duration: Int
) {
    if (withDelay) delay(index * (400..900L).random())
    animatable.animateTo(
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
            },
            repeatMode = RepeatMode.Restart
        )
    )
}

@Composable
fun MainMenuScreen(onPlayClick: () -> Unit, onRecordsClick: () -> Unit, onAuthorClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray), contentAlignment = Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MenuComponent(modifier = Modifier.align(CenterHorizontally), text = "Play") {
                onPlayClick()
            }
            Divider(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            MenuComponent(modifier = Modifier.align(CenterHorizontally), text = "Records") {
                onRecordsClick()
            }
            Divider(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            MenuComponent(modifier = Modifier.align(CenterHorizontally), text = "Author") {
                onAuthorClick()
            }
        }
    }
}