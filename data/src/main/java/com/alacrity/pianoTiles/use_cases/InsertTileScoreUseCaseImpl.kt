package com.alacrity.pianoTiles.use_cases

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.repository.Repository
import javax.inject.Inject

class InsertTileScoreUseCaseImpl@Inject constructor(
    private val repository: Repository
): InsertTileScoreUseCase {

    override suspend fun invoke(playerScore: PlayerScore) {
        repository.insertPlayerScoreToDB(playerScore)
    }


}