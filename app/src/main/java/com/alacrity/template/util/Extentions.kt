package com.alacrity.template.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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