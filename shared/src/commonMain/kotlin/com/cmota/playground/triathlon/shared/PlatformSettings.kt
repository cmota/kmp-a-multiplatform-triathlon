package com.cmota.playground.triathlon.shared

import com.cmota.playground.triathlon.shared.data.SettingsRepository

expect object PlatformSettings {

    val settingsRepository: SettingsRepository

    fun createSettingsRepository(): SettingsRepository
}