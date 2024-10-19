package com.tmdb.mobile.entry

import androidx.lifecycle.viewModelScope
import com.tmdb.designsystem.utils.UserMessageManager
import com.tmdb.mobile.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainEvent>() {
    private val userMessageManager by lazy { UserMessageManager }

    fun userMessageShown() {
        userMessageManager.userMessageShown()
    }

    init {
        handelUserMessage()
    }

    private fun handelUserMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            userMessageManager
                .messages
                .collectLatest { message ->
                    if (message != null) {
                        emitEvent(MainEvent.UserMessage(message))
                    } else {
                        emitEvent(MainEvent.Idle)
                    }
                }
        }
    }
}