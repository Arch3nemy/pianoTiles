package com.alacrity.pianoTiles.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alacrity.pianoTiles.entity.PlayerScoreTableItem

@Database(entities = [PlayerScoreTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerScoreDao(): PlayerScoreDao

}