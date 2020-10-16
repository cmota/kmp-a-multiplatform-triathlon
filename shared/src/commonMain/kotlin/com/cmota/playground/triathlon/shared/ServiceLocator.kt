package com.cmota.playground.triathlon.shared

import com.cmota.playground.triathlon.shared.data.ConferencesAPI
import com.cmota.playground.triathlon.shared.domain.GetConferences
import com.cmota.playground.triathlon.shared.domain.dao.ConferenceDAO
import com.cmota.playground.triathlon.shared.presentation.ConferenceListPresenter
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {

    private val conferencesAPI by lazy { ConferencesAPI() }

    private val conferenceDao by lazy {
        val db = PlatformDatabase().createDatabase()
        if (db == null) {
            null
        } else {
            ConferenceDAO(db)
        }
    }

    private val getConferences: GetConferences
        get() = GetConferences(conferencesAPI, conferenceDao)

    val getConferencePresenter: ConferenceListPresenter
        get() = ConferenceListPresenter(getConferences)
}