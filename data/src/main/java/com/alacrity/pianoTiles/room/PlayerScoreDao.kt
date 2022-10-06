package com.alacrity.pianoTiles.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alacrity.pianoTiles.entity.PlayerScoreTableItem

@Dao
interface PlayerScoreDao {

        @Query("SELECT * FROM tileScores")
        fun getAll(): List<PlayerScoreTableItem>

        @Insert
        fun insertAll(vararg numbersWithFact: PlayerScoreTableItem)

        @Delete
        fun delete(numberWithFact: PlayerScoreTableItem)


}