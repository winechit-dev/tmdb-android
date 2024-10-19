package com.tmdb.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

fun LazyListScope.moviesLoading() {
    items(
        items = (1..3).toList(),
        key = { it },
        contentType = { "loading" }
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = (LocalConfiguration.current.screenWidthDp / 3).dp)
                .aspectRatio(124f / 188f)
        )
    }
}