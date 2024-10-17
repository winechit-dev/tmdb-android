package com.tmdb.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import kotlinx.serialization.Serializable

@Serializable
data object Favorites

@Composable
fun FavoritesScreen() {
}

@Composable
internal fun FavoritesContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {

    }
}


@ThemePreviews
@Composable
internal fun FavoritesContentPreview() {
    AppPreviewWrapper {
        FavoritesContent()
    }
}