package com.cmota.playground.triathlon.shared.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class PresenterCoroutineScope(coroutineContext: CoroutineContext) : CoroutineScope {

    private var onViewDetachJob = Job()

    override val coroutineContext: CoroutineContext = coroutineContext + onViewDetachJob
}
