package com.cmota.playground.triathlon.shared

import com.cmota.playground.triathlon.shared.data.SettingsRepository
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults

actual object PlatformSettings {

    actual val settingsRepository : SettingsRepository by lazy {
        createSettingsRepository()
    }

    actual fun createSettingsRepository(): SettingsRepository {
        return SettingsRepository(AppleSettings(NSUserDefaults.standardUserDefaults))
    }
}