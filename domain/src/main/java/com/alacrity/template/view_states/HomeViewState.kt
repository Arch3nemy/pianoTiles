package com.alacrity.template.view_states

import com.alacrity.template.entity.PlayerScore


sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    object Paused : MainViewState()
    object MainMenu : MainViewState()
    object AuthorCredits : MainViewState()

    data class ScoreTable(val scores: List<PlayerScore>) : MainViewState()
    data class GameScreen(val isTilesActive: Boolean) : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()

}