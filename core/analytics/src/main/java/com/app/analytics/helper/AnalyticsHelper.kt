package com.app.analytics.helper

import com.app.analytics.AnalyticsEvent

interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
}
