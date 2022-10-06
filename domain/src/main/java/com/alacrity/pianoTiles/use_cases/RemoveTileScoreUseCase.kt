package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore

interface RemoveTileScoreUseCase {

    suspend operator fun invoke(playerScore: PlayerScore)

}