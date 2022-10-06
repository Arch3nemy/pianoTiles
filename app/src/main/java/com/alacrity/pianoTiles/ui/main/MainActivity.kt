package com.alacrity.pianoTiles.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.pianoTiles.App
import com.alacrity.pianoTiles.PianoTilesApp
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            PianoTilesApp(context = this, homeViewModel = mainViewModel)
        }
    }

}