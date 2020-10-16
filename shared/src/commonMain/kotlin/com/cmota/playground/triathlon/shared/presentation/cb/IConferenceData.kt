package com.cmota.playground.triathlon.shared.presentation.cb

import com.cmota.playground.triathlon.shared.data.entities.Conference

interface IConferenceData {

    fun onConferenceDataFetched(conferences: List<Conference>)

    fun onConferenceDataFailed(e: Exception)
}