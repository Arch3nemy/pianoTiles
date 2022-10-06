package com.alacrity.template.repository

import com.alacrity.template.entity.PlayerScore

interface Repository {

    fun getPlayerScoresFromDB(): List<PlayerScore>

    fun insertPlayerScoreToDB(playerScore: PlayerScore)

}