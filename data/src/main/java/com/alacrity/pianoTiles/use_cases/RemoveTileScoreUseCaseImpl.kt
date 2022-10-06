package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.repository.Repository
import javax.inject.Inject

class RemoveTileScoreUseCaseImpl @Inject constructor(
    private val repository: Repository
): RemoveTileScoreUseCase {

    override suspend fun invoke(playerScore: PlayerScore) = repository.removePlayerScoreFromDB(playerScore)

}