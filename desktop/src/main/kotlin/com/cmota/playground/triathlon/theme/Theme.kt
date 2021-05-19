package com.cmota.playground.triathlon.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {

  MaterialTheme(
      colors = lightColors(),
      typography = typography,
      content = content
  )
}