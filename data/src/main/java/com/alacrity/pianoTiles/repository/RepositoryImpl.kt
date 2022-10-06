package com.alacrity.pianoTiles.repository

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.entity.toRawItems
import com.alacrity.pianoTiles.room.AppDatabase
import com.alacrity.pianoTiles.utils.toTableItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db: AppDatabase) : Repository {

    override fun getPlayerScoresFromDB(): List<PlayerScore> = db.playerScoreDao().getAll().toRawItems()

    override fun insertPlayerScoreToDB(playerScore: PlayerScore) = db.playerScoreDao().insertAll(playerScore.toTableItem())

    override fun removePlayerScoreFromDB(playerScore: PlayerScore) = db.playerScoreDao().delete(playerScore.toTableItem())

}