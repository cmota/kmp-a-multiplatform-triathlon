package com.cmota.playground.triathlon.shared.domain

import com.cmota.playground.triathlon.shared.Gutenberg
import com.cmota.playground.triathlon.shared.PlatformSettings.settingsRepository
import com.cmota.playground.triathlon.shared.data.ConferencesAPI
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.shared.domain.dao.ConferenceDAO
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val TAG = "GetConferences"

class GetConferences(private val api: ConferencesAPI, private val dao: ConferenceDAO?) {

    suspend operator fun invoke(onSuccess: (List<Conference>) -> Unit, onFailure: (Exception) -> Unit) {
        try {
            val result = api.fetchConferences()
            val conferences = Json.decodeFromString<List<Conference>>(result)

            Gutenberg.d(TAG, "Result:$conferences")

            if (dao != null) {
                for (conference in conferences) {
                    dao.insertOrReplace(conference)
                }
            }

            Gutenberg.d(TAG, "Only online conferences=${settingsRepository.shouldShowOnlyOnlineConferences()}")

            //Check current state of toggle only online
            val availableConferences = if (settingsRepository.shouldShowOnlyOnlineConferences()) {
                conferences.filter { !it.isCanceled() }
            } else {
                conferences
            }

            coroutineScope {
                onSuccess(availableConferences)
            }
        } catch (e: Exception) {
            coroutineScope {
                if (dao == null) {
                    onFailure(e)
                    return@coroutineScope
                }

                val conferences = dao.getAllConferences()
                if (conferences.isEmpty()) {
                    onFailure(e)
                } else {
                    onSuccess(conferences)
                }
            }
        }
    }
}