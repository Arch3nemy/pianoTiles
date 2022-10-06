package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore

interface GetTileScoresUseCase {

    suspend operator fun invoke(): List<PlayerScore>

}