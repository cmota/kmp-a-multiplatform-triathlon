package com.cmota.playground.triathlon.shared.domain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val defaultDispatcher: CoroutineContext
    get() = Dispatchers.Default

actual val uiDispatcher: CoroutineContext
    get() = Dispatchers.Main