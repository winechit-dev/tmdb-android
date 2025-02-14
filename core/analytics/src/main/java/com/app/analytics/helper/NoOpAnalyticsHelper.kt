package com.app.analytics.helper

import com.app.analytics.AnalyticsEvent

class NoOpAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) = Unit
}
