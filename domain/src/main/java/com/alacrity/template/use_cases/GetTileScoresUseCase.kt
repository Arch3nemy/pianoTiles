package com.alacrity.template.use_cases

import com.alacrity.template.entity.PlayerScore

interface GetTileScoresUseCase {

    suspend operator fun invoke(): List<PlayerScore>

}