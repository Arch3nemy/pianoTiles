package com.alacrity.template

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.alacrity.template.ui.main.MainViewModel
import com.alacrity.template.ui.main.models.*
import com.alacrity.template.ui.main.views.*
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
        MainViewState.Paused -> {
            LoadingView()
        }
        MainViewState.AuthorCredits -> {
            AuthorView()
        }
        is MainViewState.ScoreTable -> {
            ScoreTableView((state as MainViewState.ScoreTable).scores)
        }
        MainViewState.MainMenu -> {
            MainMenuScreen(
                onPlayClick = { viewModel.startGame() },
                onRecordsClick = { viewModel.showScoreTable() },
                onAuthorClick = { viewModel.showAuthorCredits() }
            )
        }
        is MainViewState.GameScreen -> {
            AnimatedTiles(
                gameState = gameState,
                onTileClick = { viewModel.earnPoints() },
                onGameLost = { viewModel.endGame() },
                onRestartGameClick = { viewModel.restartGame() },
                onBackToMenuClick = { viewModel.backToMenu() }
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
        if(state is MainViewState.GameScreen || state is MainViewState.AuthorCredits || state is MainViewState.ScoreTable) {
            viewModel.backToMenu()
        }
    }

}
