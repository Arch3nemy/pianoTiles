package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore

interface InsertTileScoreUseCase {

    suspend operator fun invoke(playerScore: PlayerScore)

}