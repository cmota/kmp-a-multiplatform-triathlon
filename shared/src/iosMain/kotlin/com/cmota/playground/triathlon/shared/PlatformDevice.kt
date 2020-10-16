package com.cmota.playground.triathlon.shared

import platform.UIKit.UIDevice

actual fun deviceName() = "${UIDevice.currentDevice().name}-${UIDevice.currentDevice().model}"
