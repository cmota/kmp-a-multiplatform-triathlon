package com.cmota.playground.triathlon.shared.data

import com.russhwolf.settings.Settings

private const val SETTING_ONLY_ONLINE = "setting_show_only_online"

class SettingsRepository(settings: Settings) {

    private val appSettings: Settings = createAppSettings(settings)

    private fun createAppSettings(settings: Settings): Settings {
        settings.putBoolean(SETTING_ONLY_ONLINE, false)
        return settings
    }

    fun shouldShowOnlyOnlineConferences() = appSettings.getBoolean(SETTING_ONLY_ONLINE, false)

    fun onlyOnlineConferences(state: Boolean) {
        appSettings.putBoolean(SETTING_ONLY_ONLINE, state)
    }
}