package com.alacrity.template.ui.main

import androidx.compose.runtime.MutableState
import com.alacrity.template.ui.main.models.GameState
import com.alacrity.template.ui.main.models.MainEvent
import com.alacrity.template.ui.main.models.isAnimated
import com.alacrity.template.ui.main.models.points
import com.alacrity.template.use_cases.GetFactAboutNumberUseCase
import com.alacrity.template.util.BaseViewModel
import com.alacrity.template.view_states.MainViewState
import com.alacrity.template.view_states.MainViewState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getFactAboutNumberUseCase: GetFactAboutNumberUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState
    val gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState(true, 0))

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            else -> Unit
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                launch {
                    _viewState.value = FinishedLoading(true)
                }
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.ToggleAnim -> {
                gameState.value = gameState.value.copy(isAnimated = !gameState.isAnimated())
            }
            MainEvent.RestartGame -> {
                launch {
                    _viewState.value = Loading
                    gameState.value = GameState(isAnimated = true, points = 0)
                    delay(300)
                    _viewState.value = FinishedLoading(true)
                }
            }
            MainEvent.EndGame -> {
                gameState.value = GameState(isAnimated = false, points = gameState.points())
            }
            MainEvent.EarnPoints -> {
                gameState.value = GameState(
                    isAnimated = gameState.isAnimated(),
                    points = gameState.points() + 100
                )
            }
            else -> Unit
        }
    }

}