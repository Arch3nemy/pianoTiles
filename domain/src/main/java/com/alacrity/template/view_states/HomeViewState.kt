package com.alacrity.template.view_states

import com.alacrity.template.entity.NumberWithFact


sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    object Refreshing: MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    object NoItems: MainViewState()
    data class FinishedLoading(val numberWithFact: NumberWithFact) : MainViewState()
}