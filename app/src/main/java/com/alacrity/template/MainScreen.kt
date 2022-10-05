package com.alacrity.template

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alacrity.template.ui.main.MainViewModel
import com.alacrity.template.ui.main.models.*
import com.alacrity.template.ui.main.views.AnimatedTiles
import com.alacrity.template.ui.main.views.LoadingView
import com.alacrity.template.view_states.MainViewState

@Composable
fun MainScreen(
    context: Context,
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    when (state) {
        MainViewState.Loading -> {
            LoadingView()
        }
        is MainViewState.FinishedLoading -> {
            AnimatedTiles(
                gameState = gameState,
                onTileClick = { viewModel.earnPoints() },
                onGameLost = { viewModel.endGame() },
                onRestartGameClick = { viewModel.restartGame() },
            )
        }
        is MainViewState.Error -> {
            /* ShowErrorView */
        }
        is MainViewState.Paused -> {

        }
        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

    BackHandler {
        viewModel.toggleAnim()
    }

}
