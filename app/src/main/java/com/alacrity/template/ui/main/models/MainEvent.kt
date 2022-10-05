package com.alacrity.template.ui.main.models

import com.alacrity.template.BaseEvent
import com.alacrity.template.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

    object ToggleAnim : MainEvent()

    object RestartGame : MainEvent()

    object EndGame : MainEvent()

    object EarnPoints : MainEvent()

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