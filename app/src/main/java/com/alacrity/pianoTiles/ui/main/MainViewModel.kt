package com.alacrity.pianoTiles.ui.main

import com.alacrity.pianoTiles.entity.PlayerScore
import com.alacrity.pianoTiles.ui.main.models.*
import com.alacrity.pianoTiles.use_cases.GetTileScoresUseCase
import com.alacrity.pianoTiles.use_cases.InsertTileScoreUseCase
import com.alacrity.pianoTiles.use_cases.RemoveTileScoreUseCase
import com.alacrity.pianoTiles.util.BaseViewModel
import com.alacrity.pianoTiles.util.maxScore
import com.alacrity.pianoTiles.view_states.MainViewState
import com.alacrity.pianoTiles.view_states.MainViewState.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTileScoresUseCase: GetTileScoresUseCase,
    private val insertTileScoreUseCase: InsertTileScoreUseCase,
    private val removeTileScoreUseCase: RemoveTileScoreUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState
    val gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState(true, 0))
    private val scoreList = mutableListOf<PlayerScore>()

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is MainMenu -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is GameScreen -> currentState.reduce(event)
            is AuthorCredits -> currentState.reduce(event)
            is ScoreTable -> currentState.reduce(event)
            else -> Unit
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                loadData()
            }
            else -> Unit
        }
    }

    private fun MainMenu.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.StartGame -> {
                gameState.value = GameState(isAnimated = true, points = 0)
                _viewState.value = GameScreen(true)
            }
            MainEvent.ShowAuthorCredits -> {
                _viewState.value = AuthorCredits
            }
            MainEvent.ShowScoreTable -> {
                _viewState.value = ScoreTable(scoreList)
            }
            else -> Unit
        }
    }

    private fun ScoreTable.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.BackToMenu -> {
                _viewState.value = MainMenu
            }
            is MainEvent.RemovePlayerScore -> {
                removePlayerScore(event.playerScore)
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun GameScreen.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.ToggleAnim -> {
                gameState.value = gameState.value.copy(isAnimated = !gameState.isAnimated())
            }
            MainEvent.RestartGame -> {
                launch {
                    _viewState.value = Paused
                    gameState.value = GameState(isAnimated = true, points = 0)
                    delay(300)
                    _viewState.value = GameScreen(true)
                }
            }
            MainEvent.EndGame -> {
                gameState.value = GameState(isAnimated = false, points = gameState.points())
                savePlayerScore(gameState.points())
            }
            MainEvent.EarnPoints -> {
                gameState.value = GameState(
                    isAnimated = gameState.isAnimated(),
                    points = gameState.points() + 100
                )
            }
            MainEvent.BackToMenu -> {
                _viewState.value = MainMenu
            }
            else -> Unit
        }
    }

    private fun loadData() {
        launch(
            logSuccess = "Successfully received data from DB",
            logError = "Error getting data from DB",
            onSuccess = {
                scoreList.addAll(it)
                _viewState.value = MainMenu
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            getTileScoresUseCase()
        }
    }

    private fun savePlayerScore(score: Int) {
        if (score > scoreList.maxScore()) {
            launch(
                logSuccess = "Successfully saved data to DB",
                logError = "Error saving data to DB",
                onFailure = {
                    _viewState.value = Error(it)
                }
            ) {
                val playerScore = PlayerScore(UUID.randomUUID().toString(), score)
                scoreList.add(playerScore)
                insertTileScoreUseCase(playerScore)
            }
        }
    }

    private fun removePlayerScore(score: PlayerScore) {
        launch(
            logSuccess = "Successfully removed data from DB",
            logError = "Error removing data from DB",
            onSuccess = {
                scoreList.remove(score)
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            removeTileScoreUseCase(score)
        }
    }

    private fun AuthorCredits.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.BackToMenu -> {
                _viewState.value = MainMenu
            }
            else -> Unit
        }
    }

}