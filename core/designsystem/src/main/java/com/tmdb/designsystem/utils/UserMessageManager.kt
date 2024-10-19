package com.tmdb.designsystem.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserMessageManager {
    private val _messages = MutableStateFlow<String?>(null)
    val messages: StateFlow<String?> get() = _messages.asStateFlow()

    fun showMessage(message: String) {
        if (message.isNotBlank() && message != "Job was cancelled") {
            _messages.value = message
        }
    }

    fun userMessageShown() {
        _messages.value = null
    }
}

