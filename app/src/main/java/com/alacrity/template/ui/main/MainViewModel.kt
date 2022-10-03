package com.alacrity.template.ui.main

import com.alacrity.template.ui.main.models.MainEvent
import com.alacrity.template.use_cases.GetFactAboutNumberUseCase
import com.alacrity.template.util.BaseViewModel
import com.alacrity.template.view_states.MainViewState
import com.alacrity.template.view_states.MainViewState.*
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getFactAboutNumberUseCase: GetFactAboutNumberUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is NoItems -> currentState.reduce(event)
            is Refreshing -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                getFactAboutNumber(4)
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun NoItems.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun Refreshing.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun getFactAboutNumber(number: Int) {
        launch(
            logError = "Error Getting Fact about number $number",
            logSuccess = "Successfully received fact about number $number",
            onSuccess = {
                _viewState.value = FinishedLoading(it).also {
                    Timber.d("fact ${it.numberWithFact.fact}")
                }
            },
            onFailure = {
                _viewState.value = Error(it).also {
                    Timber.d("Error:: $it")
                }
            }
        ) {
            getFactAboutNumberUseCase(number)
        }
    }

}