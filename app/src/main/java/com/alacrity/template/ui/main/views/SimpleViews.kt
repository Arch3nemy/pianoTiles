package com.alacrity.template.ui.main.views

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.FX_KEY_CLICK
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alacrity.template.entity.PlayerScore
import com.alacrity.template.theme.CrystalGray
import com.alacrity.template.theme.TemplateTypography
import com.alacrity.template.theme.EndMenuColor
import com.alacrity.template.ui.main.models.TileState
import com.alacrity.template.view_states.MainViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Tile(
    modifier: Modifier,
    translation: Float,
    isAnimated: Boolean,
    tileWidth: Int = 100,
    eventualTileHeight: Int = 180,
    tileDisableDelay: Long = 3000,
    onTileNotPressed: () -> Unit,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var tileState by remember { mutableStateOf(TileState(Color.DarkGray, true)) }
    var tileHeight by remember { mutableStateOf(0) }

    fun disableTileForDelay() {
        scope.launch {
            tileState = TileState(Color.White, false)
            delay(tileDisableDelay)
            tileState = TileState(Color.DarkGray, true)
        }
    }

    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(width = tileWidth.dp, height = tileHeight.dp)
            .graphicsLayer {
                translationY = translation
                if (translation > 0) tileHeight = eventualTileHeight
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

                val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                audioManager.playSoundEffect(FX_KEY_CLICK, 1.0f)

                onClick()
                disableTileForDelay()
            }
    )


}

@Composable
fun RestartGameLabel(modifier: Modifier, onRestartGameClick: () -> Unit) {
    Box(modifier = modifier.wrapContentSize()) {
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .align(Center),
            onClick = { onRestartGameClick() }
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))

            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "restart game button",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BackToMenuLabel(modifier: Modifier, onBackToMenuPressed: () -> Unit) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = 10.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomCenter),
            onClick = { onBackToMenuPressed() }
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "back to menu button",
                    tint = Color.White
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Center
    ) {
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

@Composable
fun MenuComponent(modifier: Modifier, text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(60.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        backgroundColor = CrystalGray,
    ) {
        Box(modifier = modifier) {
            Text(
                modifier = Modifier.align(Center),
                text = text,
                style = TemplateTypography.h3,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun EndGameMenu(
    modifier: Modifier,
    points: Int,
    onRestartGameClick: () -> Unit,
    onBackToMenuClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 10.dp), contentAlignment = Center
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(150.dp),
            backgroundColor = CrystalGray,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$points",
                        style = TemplateTypography.h4,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Points achieved",
                        style = TemplateTypography.h2,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RestartGameLabel(
                        modifier = Modifier
                    ) {
                        onRestartGameClick()
                    }

                    BackToMenuLabel(modifier = Modifier) {
                        onBackToMenuClick()
                    }
                }
            }
        }
    }
}

@Composable
fun AuthorView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color.LightGray,
        ) {
            val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                val str =
                    "Hello there, this game is developed by Ivan, check out my github https://github.com/ArchEnemy04 " +
                            "for more interesting applications that use brand new technologies."
                val startIndex = str.indexOf("https")
                val endIndex = startIndex + 30
                append(str)
                addStyle(
                    style = SpanStyle(
                        color = Color(0xff64B5F6),
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline
                    ), start = startIndex, end = endIndex
                )

                addStringAnnotation(
                    tag = "URL",
                    annotation = "https://github.com/archenemy04",
                    start = startIndex,
                    end = endIndex
                )

            }

            val uriHandler = LocalUriHandler.current

            ClickableText(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .align(Center),
                text = annotatedLinkString,
                style = TemplateTypography.h5,
                onClick = {
                    annotatedLinkString
                        .getStringAnnotations("URL", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
    }
}

@Composable
fun ScoreTableView(scores: List<PlayerScore>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray), contentAlignment = Center
    ) {
        scores.forEach {
            ScoreView(score = it)
        }
    }
}

@Composable
fun ScoreView(score: PlayerScore) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .width(340.dp)
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = CrystalGray,
        ) {
            Box(modifier = Modifier) {
                Text(
                    modifier = Modifier.align(Center),
                    text = score.score.toString(),
                    style = TemplateTypography.h3,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

