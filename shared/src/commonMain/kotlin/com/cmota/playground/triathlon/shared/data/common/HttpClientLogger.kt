package com.cmota.playground.triathlon.shared.data.common

import com.cmota.playground.triathlon.shared.Gutenberg
import io.ktor.client.features.logging.*

private const val TAG = "HttpClientLogger"

public object HttpClientLogger: Logger {

    override fun log(message: String) {
        Gutenberg.d(TAG, message)
    }
}