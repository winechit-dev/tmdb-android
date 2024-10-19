package com.tmdb.designsystem.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

enum class ButtonEvent { Pressed, Idle }

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.bounceClick() = composed {
    var buttonEvent by remember { mutableStateOf(ButtonEvent.Idle) }
    val scale by animateFloatAsState(
        if (buttonEvent == ButtonEvent.Pressed) 0.9f else 1f,
        label = buttonEvent.name
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonEvent) {
            awaitPointerEventScope {
                buttonEvent = if (buttonEvent == ButtonEvent.Pressed) {
                    waitForUpOrCancellation()
                    ButtonEvent.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonEvent.Pressed
                }
            }
        }
}


fun Modifier.bounceClick(enabled: Boolean): Modifier {
    return if (enabled) this.bounceClick() else this
}