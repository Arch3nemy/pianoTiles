package com.alacrity.pianoTiles.repository

import com.alacrity.pianoTiles.entity.PlayerScore

interface Repository {

    fun getPlayerScoresFromDB(): List<PlayerScore>

    fun insertPlayerScoreToDB(playerScore: PlayerScore)

    fun removePlayerScoreFromDB(playerScore: PlayerScore)

}