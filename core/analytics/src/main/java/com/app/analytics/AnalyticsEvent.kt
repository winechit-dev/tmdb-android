package com.app.analytics


data class AnalyticsEvent(
    val type: String,
    val extras: List<Param> = emptyList(),
){
    class Types {
        companion object {
            const val SCREEN_VIEW = "screen_view" // (extras: SCREEN_NAME)
        }
    }

    data class Param(val key: String, val value: String)

    class ParamKeys {
        companion object {
            const val SCREEN_NAME = "screen_name"
        }
    }
}
