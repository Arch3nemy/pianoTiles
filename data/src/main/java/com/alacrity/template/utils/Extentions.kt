package com.alacrity.template.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alacrity.template.entity.PlayerScore
import com.alacrity.template.room.PlayerScoreTableItem

fun PlayerScore.toTableItem(): PlayerScoreTableItem {
    return PlayerScoreTableItem(id, score)
}