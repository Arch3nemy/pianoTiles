package com.alacrity.pianoTiles.utils

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.entity.PlayerScoreTableItem

fun PlayerScore.toTableItem(): PlayerScoreTableItem {
    return PlayerScoreTableItem(id, score)
}