package com.alacrity.pianoTiles.ui.main.models

import com.alacrity.pianoTiles.BaseEvent
import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

    object ToggleAnim : MainEvent()

    object RestartGame : MainEvent()

    object EndGame : MainEvent()

    object EarnPoints : MainEvent()

    object StartGame : MainEvent()

    object BackToMenu : MainEvent()

    object ShowAuthorCredits : MainEvent()

    object ShowScoreTable : MainEvent()

    data class RemovePlayerScore(val playerScore: PlayerScore) : MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}

fun MainViewModel.toggleAnim() {
    obtainEvent(MainEvent.ToggleAnim)
}

fun MainViewModel.restartGame() {
    obtainEvent(MainEvent.RestartGame)
}

fun MainViewModel.endGame() {
    obtainEvent(MainEvent.EndGame)
}

fun MainViewModel.earnPoints() {
    obtainEvent(MainEvent.EarnPoints)
}

fun MainViewModel.startGame() {
    obtainEvent(MainEvent.StartGame)
}

fun MainViewModel.backToMenu() {
    obtainEvent(MainEvent.BackToMenu)
}

fun MainViewModel.showAuthorCredits() {
    obtainEvent(MainEvent.ShowAuthorCredits)
}

fun MainViewModel.showScoreTable() {
    obtainEvent(MainEvent.ShowScoreTable)
}

fun MainViewModel.removeItem(playerScore: PlayerScore) {
    obtainEvent(MainEvent.RemovePlayerScore(playerScore))
}