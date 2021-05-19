package com.cmota.playground.triathlon.shared

import data.ConferenceDb

expect class PlatformDatabase() {

    //It can be null since sqldelight for js is not published at the moment
    fun createDatabase(): ConferenceDb?
}