package com.alacrity.template.ui.main.models

import kotlinx.coroutines.flow.MutableStateFlow

data class GameState(val isAnimated: Boolean, val points: Int)

fun MutableStateFlow<GameState>.points(): Int = value.points

fun MutableStateFlow<GameState>.isAnimated(): Boolean = value.isAnimated