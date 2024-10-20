@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.favorites

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.designsystem.R
import com.tmdb.designsystem.components.AppIconButton
import com.tmdb.designsystem.theme.AppPreviewWithSharedTransitionLayout
import com.tmdb.designsystem.theme.LocalEntryPadding
import com.tmdb.designsystem.theme.LocalNavAnimatedVisibilityScope
import com.tmdb.designsystem.theme.LocalSharedTransitionScope
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.AppSharedElementKey
import com.tmdb.designsystem.utils.AppSharedElementType
import com.tmdb.designsystem.utils.bounceClick
import com.tmdb.designsystem.utils.detailBoundsTransform
import com.tmdb.designsystem.utils.networkImagePainter
import com.tmdb.domain.model.FavoriteMovieModel
import kotlinx.serialization.Serializable

@Serializable
data object Favorites

@Composable
fun FavoritesScreen(
    onNavigateMovieDetails: (model: FavoriteMovieModel, type: String) -> Unit
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favorites by viewModel.favorites.collectAsState()

    FavoritesContent(
        favorites = favorites,
        onFavoriteToggle = viewModel::onToggleFavorite,
        onNavigateMovieDetails = onNavigateMovieDetails
    )
}

@Composable
internal fun FavoritesContent(
    modifier: Modifier = Modifier,
    favorites: List<FavoriteMovieModel>,
    onFavoriteToggle: (FavoriteMovieModel) -> Unit = {},
    onNavigateMovieDetails: (model: FavoriteMovieModel, type: String) -> Unit = { _, _ -> }
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp)
            )
        }
    ) { innerPadding ->
        val top =
            LocalEntryPadding.current.calculateTopPadding() + innerPadding.calculateTopPadding()
        val bottom =
            LocalEntryPadding.current.calculateBottomPadding() + innerPadding.calculateBottomPadding()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = bottom),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(top = top)
                .padding(horizontal = 20.dp)
        ) {
            items(
                items = favorites,
                key = { it.movieId },
                contentType = { "" }
            ) { model ->
                FavoriteMovieItem(
                    model = model,
                    onFavoriteToggle = onFavoriteToggle,
                    onNavigateMovieDetails = onNavigateMovieDetails
                )
            }
        }

    }
}

@Composable
private fun FavoriteMovieItem(
    modifier: Modifier = Modifier,
    model: FavoriteMovieModel,
    onFavoriteToggle: (FavoriteMovieModel) -> Unit,
    onNavigateMovieDetails: (model: FavoriteMovieModel, type: String) -> Unit
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")

    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(124f / 188f)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = AppSharedElementKey(
                            id = model.movieId.toString() + AppSharedElementType.Favorite,
                            type = AppSharedElementType.Bounds
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = detailBoundsTransform,
                    enter = fadeIn(),
                    exit = fadeOut()
                ),
            onClick = { onNavigateMovieDetails(model, AppSharedElementType.Favorite.toString()) }
        ) {
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = networkImagePainter(model.posterPath),
                    contentDescription = "poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
                AppIconButton(
                    icon = if (model.favorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off,
                    onClick = { onFavoriteToggle(model) },
                    modifier = Modifier
                        .bounceClick()
                        .padding(8.dp)
                )
            }
        }
    }

}


@ThemePreviews
@Composable
internal fun FavoritesContentPreview() {
    AppPreviewWithSharedTransitionLayout {
        FavoritesContent(
            favorites = (1..8).toList().map {
                FavoriteMovieModel(
                    movieId = it,
                    name = "FavoriteMovie $it",
                    posterPath = "",
                    favorite = it == 1
                )
            }
        )
    }
}