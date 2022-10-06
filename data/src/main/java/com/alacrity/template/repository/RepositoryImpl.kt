package com.alacrity.template.repository

import com.alacrity.template.entity.PlayerScore
import com.alacrity.template.room.AppDatabase
import com.alacrity.template.room.toRawItems
import com.alacrity.template.utils.toTableItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db: AppDatabase) : Repository {

    override fun getPlayerScoresFromDB(): List<PlayerScore> = db.playerScoreDao().getAll().toRawItems()

    override fun insertPlayerScoreToDB(playerScore: PlayerScore) = db.playerScoreDao().insertAll(playerScore.toTableItem())

}