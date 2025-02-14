package com.app.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.app.analytics.helper.AnalyticsHelper
import com.app.analytics.helper.LocalAnalyticsHelper

/**
 * Classes and functions associated with analytics events for the UI.
 */
fun AnalyticsHelper.logScreenView(eventType: String) {
    logEvent(
        AnalyticsEvent(type = eventType)
    )
}

fun AnalyticsHelper.logEvent(eventType: String) {
    logEvent(
        AnalyticsEvent(type = eventType)
    )
}

/**
 * A side-effect which records a screen view event.
 */
@Composable
fun TrackScreenViewEvent(
    eventType: String,
    analyticsHelper: AnalyticsHelper = LocalAnalyticsHelper.current,
) = DisposableEffect(Unit) {
    analyticsHelper.logScreenView(eventType)
    onDispose {}
}
