package com.cmota.playground.triathlon.androidApp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cmota.playground.triathlon.androidApp.R

sealed class BottomNavigationScreens(val route: String, @StringRes val stringResId: Int, @DrawableRes val drawResId: Int) {
  object Conferences : BottomNavigationScreens("Conferences", R.string.navigation_conferences, R.drawable.ic_emoji_people)
  object Settings : BottomNavigationScreens("Settings", R.string.navigation_settings, R.drawable.ic_settings)
}