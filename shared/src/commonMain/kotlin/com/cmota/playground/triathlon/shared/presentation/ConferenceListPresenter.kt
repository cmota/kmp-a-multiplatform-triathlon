package com.cmota.playground.triathlon.shared.presentation

import com.cmota.playground.triathlon.shared.domain.GetConferences
import com.cmota.playground.triathlon.shared.domain.defaultDispatcher
import com.cmota.playground.triathlon.shared.presentation.cb.IConferenceData
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ConferenceListPresenter(private val conferences: GetConferences,
                              private val coroutineContext: CoroutineContext = defaultDispatcher
) {

    private var view: IConferenceData? = null
    private lateinit var scope: PresenterCoroutineScope

    fun attachView(currView: IConferenceData) {
        view = currView
        scope = PresenterCoroutineScope(coroutineContext)
        fetchConferenceList()
    }

    private fun fetchConferenceList() {
        scope.launch {
            conferences(
                onSuccess = { view?.onConferenceDataFetched(it) },
                onFailure = { view?.onConferenceDataFailed(it) }
            )
        }
    }
}