package com.alacrity.template.ui.main.models

import com.alacrity.template.BaseEvent
import com.alacrity.template.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}