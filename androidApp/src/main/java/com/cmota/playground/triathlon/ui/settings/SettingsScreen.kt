package com.cmota.playground.triathlon.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmota.playground.triathlon.R
import com.cmota.playground.triathlon.theme.colorAccent
import com.cmota.playground.triathlon.theme.typography
import com.cmota.playground.triathlon.shared.PlatformSettings

@Composable
fun SettingsScreen(isChecked: MutableState<Boolean>) {

  Column(modifier = Modifier.background(color = colorAccent)) {
    Spacer(modifier = Modifier.height(16.dp))

    AddSetting(isChecked = isChecked)

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
fun AddSetting(isChecked: MutableState<Boolean>) {
  Row(
    modifier = Modifier.background(color = colorAccent),
    verticalAlignment = Alignment.CenterVertically
  ) {

    Spacer(modifier = Modifier.width(16.dp))

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1.0f)
    ) {
      Text(
        text = stringResource(id = R.string.settings_filter_online),
        style = typography.h2
      )
    }

    Row(
      modifier = Modifier.weight(0.5f),
      horizontalArrangement = Arrangement.End
    ) {
      Checkbox(checked = isChecked.value, onCheckedChange = {
        isChecked.value = it
        PlatformSettings.settingsRepository.onlyOnlineConferences(isChecked.value)
      })
    }

    Spacer(modifier = Modifier.width(16.dp))
  }
}