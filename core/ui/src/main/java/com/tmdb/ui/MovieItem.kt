@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.LocalNavAnimatedVisibilityScope
import com.tmdb.designsystem.theme.LocalSharedTransitionScope
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.AppSharedElementKey
import com.tmdb.designsystem.utils.AppSharedElementType
import com.tmdb.designsystem.utils.detailBoundsTransform
import com.tmdb.designsystem.utils.networkImagePainter

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    model: MovieUIModel,
    type: String = "",
    onClick: (MovieUIModel, String) -> Unit
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")

    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .widthIn(max = (LocalConfiguration.current.screenWidthDp / 3).dp)
                .aspectRatio(124f / 188f)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = AppSharedElementKey(
                            id = model.id.toString() + type,
                            type = AppSharedElementType.Bounds
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = detailBoundsTransform,
                    enter = fadeIn(),
                    exit = fadeOut()
                ),
            onClick = { onClick(model, type) }
        ) {
            Image(
                painter = networkImagePainter(model.posterPath),
                contentDescription = "poster",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
            )
        }
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
            onClick = { _, _ -> }
        )
    }
}