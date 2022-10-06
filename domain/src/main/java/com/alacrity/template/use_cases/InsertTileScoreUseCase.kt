package com.alacrity.template.use_cases

import com.alacrity.template.entity.PlayerScore

interface InsertTileScoreUseCase {

    suspend operator fun invoke(playerScore: PlayerScore)

}