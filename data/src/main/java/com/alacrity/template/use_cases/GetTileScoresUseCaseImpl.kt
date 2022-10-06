package com.alacrity.template.use_cases

import com.alacrity.template.entity.PlayerScore
import com.alacrity.template.repository.Repository
import javax.inject.Inject

class GetTileScoresUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetTileScoresUseCase {

    override suspend fun invoke(): List<PlayerScore> = repository.getPlayerScoresFromDB()


}