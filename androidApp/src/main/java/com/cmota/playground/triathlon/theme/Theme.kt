package com.cmota.playground.triathlon.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

  val colors = if (darkTheme) {
    darkColors()
  } else {
    lightColors()
  }

  MaterialTheme(
      colors = colors,
      typography = typography,
      content = content
  )
}