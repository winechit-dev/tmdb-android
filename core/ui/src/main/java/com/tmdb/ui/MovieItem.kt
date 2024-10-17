package com.tmdb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.networkImagePainter

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    model: MovieUIModel,
    onClick: (MovieUIModel) -> Unit
) {
    Card(
        modifier = modifier
            .widthIn(max = (LocalConfiguration.current.screenWidthDp / 3).dp)
            .aspectRatio(124f / 188f),
        onClick = { onClick(model) }
    ) {
        Image(
            painter = networkImagePainter(model.posterPath),
            contentDescription = "poster",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@ThemePreviews
@Composable
private fun MovieItemPreview() {
    AppPreviewWrapper {
        MovieItem(
            model = MovieUIModel(
                id = 1,
                posterPath = "",
                name = "",
                genreIds = emptyList()
            ),
            onClick = {}
        )
    }
}