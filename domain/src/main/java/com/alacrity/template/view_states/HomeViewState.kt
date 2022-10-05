package com.alacrity.template.view_states


sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    object Paused : MainViewState()

    data class FinishedLoading(val isTilesActive: Boolean) : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()

}