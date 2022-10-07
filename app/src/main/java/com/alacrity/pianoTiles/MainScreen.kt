package com.alacrity.pianoTiles

import android.content.Context
import android.media.AudioManager
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alacrity.pianoTiles.ui.main.MainViewModel
import com.alacrity.pianoTiles.ui.main.models.*
import com.alacrity.pianoTiles.ui.main.views.*
import com.alacrity.pianoTiles.view_states.MainViewState
import com.alacrity.pianoTiles.R

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
            AuthorView { viewModel.backToMenu() }
        }
        is MainViewState.ScoreTable -> {
            ScoreTableView((state as MainViewState.ScoreTable).scores,
                onBackPressed = { viewModel.backToMenu() }) {
                viewModel.removeItem(it)
            }
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
        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

    BackHandler {
        if (state is MainViewState.GameScreen || state is MainViewState.AuthorCredits || state is MainViewState.ScoreTable) {
            viewModel.backToMenu()
        }
    }

}
