package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.repository.Repository
import javax.inject.Inject

class GetTileScoresUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetTileScoresUseCase {

    override suspend fun invoke(): List<PlayerScore> = repository.getPlayerScoresFromDB()


}