package com.cmota.playground.triathlon.shared

import android.os.Build.MANUFACTURER
import android.os.Build.MODEL

actual fun deviceName() = "$MODEL-$MANUFACTURER"