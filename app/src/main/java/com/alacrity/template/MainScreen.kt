package com.alacrity.template

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alacrity.template.theme.TemplateTypography
import com.alacrity.template.ui.main.MainViewModel
import com.alacrity.template.ui.main.models.enterScreen
import com.alacrity.template.util.getScreenSize
import com.alacrity.template.view_states.MainViewState

@Composable
fun MainScreen(
    context: Context,
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsState()

    when (state) {
        MainViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }
        is MainViewState.FinishedLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = (state as MainViewState.FinishedLoading).numberWithFact.fact,
                    style = TemplateTypography.h1
                )
            }
        }
        is MainViewState.NoItems -> {

        }
        is MainViewState.Error -> {
            /* ShowErrorView */
        }

        is MainViewState.Refreshing -> {

        }
        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}
