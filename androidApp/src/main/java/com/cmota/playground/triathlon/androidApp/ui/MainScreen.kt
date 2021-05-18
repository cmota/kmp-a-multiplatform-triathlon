package com.cmota.playground.triathlon.androidApp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.cmota.playground.triathlon.androidApp.ConferencesViewModel
import com.cmota.playground.triathlon.androidApp.R
import com.cmota.playground.triathlon.androidApp.theme.colorAccent
import com.cmota.playground.triathlon.androidApp.theme.colorBackground
import com.cmota.playground.triathlon.androidApp.theme.colorPrimary
import com.cmota.playground.triathlon.androidApp.ui.conferences.ConferencesScreen
import com.cmota.playground.triathlon.androidApp.ui.settings.SettingsScreen

private val BottomNavigationHeight = 56.dp
private lateinit var selectedIndex: MutableState<Int>

@Composable
fun MainScreen(viewModel: ConferencesViewModel,
               openUrl: (website: String) -> Unit) {

  val navController = rememberNavController()
  val bottomNavigationItems = listOf(
      BottomNavigationScreens.Conferences,
      BottomNavigationScreens.Settings
    )

  Scaffold(
    topBar = {
      TopAppBar(
        backgroundColor = colorPrimary,
        contentColor = colorAccent
      ) {
        Spacer(modifier = Modifier.width(16.dp))

        Text(text = stringResource(id = R.string.app_name))
      }
    },
    content = {
      Surface(
        modifier = Modifier.padding(bottom = BottomNavigationHeight),
        color = colorBackground
      ) {
        MainScreenNavigationConfigurations(
          viewModel = viewModel,
          openUrl = openUrl,
          navController = navController
        )
      }
    },
    bottomBar = {
      AppBottomNavigation(navController, bottomNavigationItems)
    }
  )
}

@Composable
private fun AppBottomNavigation(
  navController: NavHostController,
  items: List<BottomNavigationScreens>
) {
  BottomNavigation(
    backgroundColor = colorPrimary,
    contentColor = colorAccent
  ) {

    selectedIndex = remember { mutableStateOf(0) }

    items.forEachIndexed { index, screen ->

      val isSelected = selectedIndex.value == index

      BottomNavigationItem(
        modifier = Modifier.background(colorPrimary),
        icon = {
          Icon(
            painter = painterResource(id = screen.drawResId),
            contentDescription = stringResource(id = screen.stringResId)
          ) },
        label = { Text(stringResource(id = screen.stringResId)) },
        selected = isSelected,
        selectedContentColor = colorAccent,
        unselectedContentColor = colorAccent,
        alwaysShowLabel = true,
        onClick = {
          if (!isSelected) {
            selectedIndex.value = index
            navController.navigate(screen.route)
          }
        }
      )
    }
  }
}

@Composable
private fun MainScreenNavigationConfigurations(viewModel: ConferencesViewModel,
                                               openUrl: (website: String) -> Unit,
                                               navController: NavHostController) {

  val isChecked = remember { mutableStateOf(false) }
  viewModel.getConferences()

  NavHost(navController, startDestination = BottomNavigationScreens.Conferences.route) {
    composable(BottomNavigationScreens.Conferences.route) {
      ConferencesScreen(
        viewModel = viewModel,
        openUrl = openUrl
      )
    }

    composable(BottomNavigationScreens.Settings.route) {
      SettingsScreen(
        isChecked = isChecked
      )
    }
  }
}