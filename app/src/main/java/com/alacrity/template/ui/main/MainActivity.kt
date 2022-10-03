package com.alacrity.template.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.template.App
import com.alacrity.template.TemplateApp
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            TemplateApp(context = this, homeViewModel = mainViewModel)
        }
    }

}