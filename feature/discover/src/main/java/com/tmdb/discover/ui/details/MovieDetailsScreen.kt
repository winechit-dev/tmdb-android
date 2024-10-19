@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)

package com.tmdb.discover.ui.details

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.designsystem.R
import com.tmdb.designsystem.components.AppButton
import com.tmdb.designsystem.components.AppChip
import com.tmdb.designsystem.components.AppIconButton
import com.tmdb.designsystem.theme.AppPreviewWithSharedTransitionLayout
import com.tmdb.designsystem.theme.LocalNavAnimatedVisibilityScope
import com.tmdb.designsystem.theme.LocalSharedTransitionScope
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.AppSharedElementKey
import com.tmdb.designsystem.utils.AppSharedElementType
import com.tmdb.designsystem.utils.detailBoundsTransform
import com.tmdb.designsystem.utils.networkImagePainter
import com.tmdb.designsystem.utils.nonSpatialExpressiveSpring
import com.tmdb.designsystem.utils.shimmerEffect
import com.tmdb.discover.movieDetailsPreview
import com.tmdb.discover.moviesPreview
import com.tmdb.domain.model.CastModel
import com.tmdb.domain.model.GenreModel
import com.tmdb.ui.MovieItem
import com.tmdb.ui.MovieUIModel
import com.tmdb.ui.moviesLoading
import kotlin.math.roundToInt
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(val id: Int, val name: String, val posterPath: String, val type: String)

@Composable
fun MovieDetailsScreen(
    args: MovieDetails,
    onEvent: (MovieDetailsEvent) -> Unit
) {

    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsContent(
        args = args,
        uiState = uiState,
        onEvent = onEvent
    )
}

@Composable
internal fun MovieDetailsContent(
    args: MovieDetails,
    uiState: MovieDetailsUIState,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")

    with(sharedTransitionScope) {
        Scaffold(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = AppSharedElementKey(
                            id = args.id.toString() + args.type,
                            type = AppSharedElementType.Bounds
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = detailBoundsTransform,
                    exit = fadeOut(nonSpatialExpressiveSpring()),
                    enter = fadeIn(nonSpatialExpressiveSpring()),
                ),
            contentWindowInsets = WindowInsets(0),
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    navigationIcon = {
                        AppIconButton(
                            icon = R.drawable.ic_back,
                            onClick = { onEvent(MovieDetailsEvent.NavigateUp) },
                        )

                    },
                    actions = {
                        AppIconButton(
                            icon = R.drawable.ic_favorites,
                            onClick = { onEvent(MovieDetailsEvent.OnToggleFavorite(uiState.details!!)) },
                        )
                    }
                )
            }
        ) { innerPadding ->

            LazyColumn(
                contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding()),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {

                headSection(
                    args = args,
                    voteAverage = uiState.details?.voteAverage,
                    releaseDate = uiState.details?.releaseDate,
                    modifier = Modifier.aspectRatio(375f / 450f)
                )

                overviewSection(
                    overview = uiState.details?.overview,
                    video = uiState.details?.video ?: false
                )

                castSection(cast = uiState.details?.cast.orEmpty())

                categoriesSection(genres = uiState.details?.genres.orEmpty())

                moviesSection(
                    title = "Recommendations",
                    movies = uiState.recommendations,
                    onClickItem = { model, type ->
                        onEvent(
                            MovieDetailsEvent.MovieDetails(
                                model = model,
                                type = type
                            )
                        )
                    }
                )
            }
        }
    }

}

private fun LazyListScope.overviewSection(overview: String?, video: Boolean) {

    if (overview == null) {
        item {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(14.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp)
                    .width(240.dp)
                    .height(14.dp)
                    .shimmerEffect()
            )

        }
    } else if (overview.isNotBlank()) {
        item {
            Text(
                text = overview,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            if (video) {
                AppButton(
                    text = "Video trailer",
                    leadingIcon = R.drawable.ic_play,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}

fun LazyListScope.castSection(
    cast: List<CastModel>
) {
    if (cast.isEmpty()) return
    item {
        Text(
            text = "Cast",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            items(
                items = cast,
                key = { it.id },
                contentType = { "Cast" }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = networkImagePainter(it.profilePath),
                        contentDescription = "profilePath",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = it.originalName,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(65.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

private fun LazyListScope.headSection(
    modifier: Modifier = Modifier,
    args: MovieDetails,
    voteAverage: Float?,
    releaseDate: String?
) {
    item {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = networkImagePainter(args.posterPath),
                contentDescription = "poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            InnerBottomShadow()
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 40.dp)
            ) {
                VoteAverage(
                    progress = voteAverage,
                    modifier = Modifier.size(57.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = args.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    if (releaseDate == null) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(12.dp)
                                .shimmerEffect()
                        )
                    } else {
                        Text(
                            text = releaseDate,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            )
        }
    }
}

private fun LazyListScope.categoriesSection(genres: List<GenreModel>) {
    if (genres.isEmpty()) return
    item {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(top = 8.dp)

        ) {
            items(
                items = genres,
                key = { it.id },
                contentType = { "genres" }
            ) { genre ->
                AppChip(
                    label = genre.name,
                    onClick = {}
                )
            }
        }
    }
}

private fun LazyListScope.moviesSection(
    title: String,
    movies: List<MovieUIModel>?,
    onClickItem: (model: MovieUIModel, type: String) -> Unit
) {
    if (movies?.isEmpty() == true) return

    item {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            if (movies != null) {
                items(
                    items = movies,
                    key = { it.id },
                    contentType = { "Movie" }
                ) { model ->
                    MovieItem(
                        model = model,
                        type = title,
                        onClick = onClickItem
                    )
                }
            } else {
                moviesLoading()
            }
        }
    }
}

@Composable
private fun InnerBottomShadow(
    modifier: Modifier = Modifier,
) {
    val shadowGradient = Brush.verticalGradient(
        0.2f to Color.Transparent,
        1f to MaterialTheme.colorScheme.surface
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(shadowGradient)
            .height(250.dp)
    )
}

@Composable
private fun VoteAverage(
    modifier: Modifier = Modifier,
    progress: Float?,
) {
    if (progress == null || progress == 0f) return
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            progress = { progress / 10 },
            trackColor = MaterialTheme.colorScheme.outlineVariant,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = (progress * 10).roundToInt().toString().plus("%"),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
    }

}


@ThemePreviews
@Composable
private fun MovieDetailsContentPreview() {
    AppPreviewWithSharedTransitionLayout {
        MovieDetailsContent(
            args = MovieDetails(
                id = 2,
                name = "Hello .NFQ",
                posterPath = "",
                type = ""
            ),
            uiState = MovieDetailsUIState(
                details = movieDetailsPreview,
                recommendations = moviesPreview
            ),
            onEvent = {}
        )
    }
}

@ThemePreviews
@Composable
private fun MovieDetailsLoadingContentPreview() {
    AppPreviewWithSharedTransitionLayout {
        MovieDetailsContent(
            args = MovieDetails(
                id = 2,
                name = "Hello .NFQ",
                posterPath = "",
                type = ""
            ),
            uiState = MovieDetailsUIState(
                details = null
            ),
            onEvent = {}
        )
    }
}