package com.moviequest.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviequest.designsystem.theme.AppPreviewWrapper
import com.moviequest.designsystem.theme.ThemePreviews
import kotlinx.serialization.Serializable

@Serializable
data object Search

@Composable
fun SearchScreen() {
}

@Composable
internal fun SearchContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ){

    }
}


@ThemePreviews
@Composable
internal fun SearchContentPreview() {
    AppPreviewWrapper {
        SearchContent()
    }
}