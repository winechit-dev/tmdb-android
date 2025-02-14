package com.app.analytics.helper

import android.os.Bundle
import com.app.analytics.AnalyticsEvent
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

internal class FirebaseAnalyticsHelper @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
) : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEvent) {
        val bundle = Bundle()
        event.extras.forEach {
            bundle.putString(it.key, it.value)
        }

        firebaseAnalytics.logEvent(event.type, bundle)
    }
}