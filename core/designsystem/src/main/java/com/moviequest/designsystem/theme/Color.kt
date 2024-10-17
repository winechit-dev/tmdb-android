package com.moviequest.designsystem.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

val MetallicViolet = Color(0xFF540BA1)
val Violet = Color(0xFF8000FF)
val PhilippinePink = Color(0xFFFF1F8A)
val EerieBlack = Color(0xFF15151D)

fun Color.asBrush(): Brush = SolidColor(this)
