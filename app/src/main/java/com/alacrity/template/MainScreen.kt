package com.alacrity.template

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alacrity.template.ui.main.MainViewModel
import com.alacrity.template.ui.main.models.*
import com.alacrity.template.ui.main.views.LoadingAnimation
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }
        is MainViewState.FinishedLoading -> {
            LoadingAnimation(
                gameState = gameState,
                onTileClick = { viewModel.earnPoints() },
                onNonTileClick = { viewModel.endGame() },
                onRestartGameClick = { viewModel.restartGame() },)
        }
        is MainViewState.Error -> {
            /* ShowErrorView */
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
