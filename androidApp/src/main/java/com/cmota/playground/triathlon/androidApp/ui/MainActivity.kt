package com.cmota.playground.triathlon.androidApp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cmota.playground.triathlon.androidApp.ConferencesViewModel
import com.cmota.playground.triathlon.androidApp.theme.AppTheme

class MainActivity : AppCompatActivity() {

    private val viewModel: ConferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(
                content = {
                    MainScreen(
                        viewModel = viewModel,
                        openUrl = { conference -> openUrl(conference) }
                    )
                }
            )
        }
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
