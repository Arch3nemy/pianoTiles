package com.alacrity.pianoTiles.util

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import com.alacrity.pianoTiles.entity.PlayerScore

//Returns pair of screen width and height
@Composable
inline fun <reified T> getScreenSize(): Pair<T, T> {
    val configuration = LocalConfiguration.current
    with(configuration) {
        return when (T::class) {
            Int::class -> Pair(screenWidthDp as T, screenHeightDp as T)
            Dp::class -> Pair(screenWidthDp.dp as T, screenHeightDp.dp as T)
            else -> Pair(
                with(LocalDensity.current) { screenWidthDp.dp.toPx() } as T,
                with(LocalDensity.current) { screenHeightDp.dp.toPx() } as T)
        }
    }
}


fun List<PlayerScore>.maxScore(): Int {
    var max = if(isEmpty()) -1 else this[0].score
    forEach { elem ->
        if (elem.score > max) max = elem.score
    }
    return max
}