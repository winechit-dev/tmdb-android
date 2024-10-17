package com.moviequest.discover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviequest.designsystem.theme.AppPreviewWrapper
import com.moviequest.designsystem.theme.ThemePreviews
import kotlinx.serialization.Serializable

@Serializable
data object Discover

@Composable
fun DiscoverScreen() {
}

@Composable
internal fun DiscoverContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ){

    }
}


@ThemePreviews
@Composable
internal fun DiscoverContentPreview() {
    AppPreviewWrapper {
        DiscoverContent()
    }
}