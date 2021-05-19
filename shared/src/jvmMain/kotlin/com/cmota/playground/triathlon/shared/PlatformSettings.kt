package com.cmota.playground.triathlon.shared

import com.cmota.playground.triathlon.shared.data.SettingsRepository
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.JvmPreferencesSettings
import java.util.prefs.Preferences

@OptIn(ExperimentalSettingsImplementation::class)
actual object PlatformSettings {
    actual val settingsRepository: SettingsRepository by lazy {
        createSettingsRepository()
    }

    actual fun createSettingsRepository(): SettingsRepository {
        val preferences = Preferences.userRoot()
        return SettingsRepository(JvmPreferencesSettings(preferences))
    }
}