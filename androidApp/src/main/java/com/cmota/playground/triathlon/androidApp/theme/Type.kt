package com.cmota.playground.triathlon.androidApp.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = colorPrimary
    ),

    h2 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 13.sp,
        color = colorPrimary
    ),

    h3 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 13.sp,
        color = colorPrimaryTransparency
    ),

    h4 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 15.sp,
        color = colorAccent
    )
)