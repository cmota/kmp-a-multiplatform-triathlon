package com.cmota.playground.triathlon.shared

import com.cmota.playground.triathlon.shared.data.SettingsRepository
import com.russhwolf.settings.JsSettings

actual object PlatformSettings {
    actual val settingsRepository: SettingsRepository by lazy {
        createSettingsRepository()
    }

    actual fun createSettingsRepository(): SettingsRepository {
        return SettingsRepository(JsSettings())
    }
}