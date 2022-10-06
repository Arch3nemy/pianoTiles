package com.alacrity.template.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerScoreTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerScoreDao(): PlayerScoreDao

}