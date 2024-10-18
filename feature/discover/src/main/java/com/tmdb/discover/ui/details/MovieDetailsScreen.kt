@file:OptIn(ExperimentalMaterial3Api::class)

package com.tmdb.discover.ui.details

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
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.networkImagePainter
import com.tmdb.discover.MovieDetailsPreview
import com.tmdb.domain.model.CastModel
import com.tmdb.domain.model.GenreModel
import kotlin.math.roundToInt
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(val id: Int, val name: String, val posterPath: String)

@Composable
fun MovieDetailsScreen(
    id: Int,
    posterPath: String,
    onEvent: (MovieDetailsEvent) -> Unit
) {

    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsContent(
        posterPath = posterPath,
        uiState = uiState,
        onEvent = onEvent
    )
}

@Composable
internal fun MovieDetailsContent(
    posterPath: String,
    uiState: MovieDetailsUIState,
    onEvent: (MovieDetailsEvent) -> Unit
) {

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
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
        if (uiState.details != null) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding()),
                modifier = Modifier.fillMaxSize()
            ) {

                headSection(
                    posterPath = posterPath,
                    voteAverage = uiState.details.voteAverage,
                    originalTitle = uiState.details.originalTitle,
                    releaseDate = uiState.details.releaseDate,
                    modifier = Modifier
                        .aspectRatio(375f / 450f)
                        .padding(bottom = 20.dp)
                )

                overviewSection(
                    overview = uiState.details.overview,
                    video = uiState.details.video
                )

                castSection(cast = uiState.details.cast)

                categoriesSection(genres = uiState.details.genres)
            }
        }
    }
}

private fun LazyListScope.overviewSection(overview: String, video: Boolean) {
    if (overview.isNotBlank()) {
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
                .padding(top = 32.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)

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
    posterPath: String,
    voteAverage: Float,
    originalTitle: String,
    releaseDate: String
) {
    item {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = networkImagePainter(posterPath),
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
                    .padding(bottom = 20.dp)
            ) {
                VoteAverage(
                    progress = voteAverage,
                    modifier = Modifier.size(57.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = originalTitle,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = releaseDate,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
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
                .padding(top = 32.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(top = 14.dp)

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
private fun CastItem() {

}

@Composable
private fun VoteAverage(
    modifier: Modifier = Modifier,
    progress: Float,
) {

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
    AppPreviewWrapper {
        MovieDetailsContent(
            posterPath = "",
            uiState = MovieDetailsUIState(
                details = MovieDetailsPreview
            ),
            onEvent = {}
        )
    }
}