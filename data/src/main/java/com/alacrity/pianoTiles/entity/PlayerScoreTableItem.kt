package com.alacrity.pianoTiles.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alacrity.pianoTiles.entity.PlayerScore

@Entity(tableName = "tileScores")
class PlayerScoreTableItem(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "score") val score: Int
)


fun PlayerScoreTableItem.toRawItem(): PlayerScore {
    return PlayerScore(uid, score)
}

fun List<PlayerScoreTableItem>.toRawItems(): MutableList<PlayerScore> {
    val result = mutableListOf<PlayerScore>()
    forEach {
        result.add(it.toRawItem())
    }
    return result
}