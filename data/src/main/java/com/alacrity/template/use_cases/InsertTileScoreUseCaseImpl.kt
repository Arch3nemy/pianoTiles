package com.alacrity.template.use_cases

import com.alacrity.template.entity.PlayerScore
import com.alacrity.template.repository.Repository
import javax.inject.Inject

class InsertTileScoreUseCaseImpl@Inject constructor(
    private val repository: Repository
): InsertTileScoreUseCase {

    override suspend fun invoke(playerScore: PlayerScore) {
        repository.insertPlayerScoreToDB(playerScore)
    }


}