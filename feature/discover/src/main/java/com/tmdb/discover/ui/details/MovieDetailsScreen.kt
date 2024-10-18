@file:OptIn(ExperimentalMaterial3Api::class)

package com.tmdb.discover.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.theme.White
import com.tmdb.designsystem.utils.networkImagePainter
import com.tmdb.discover.MovieDetailsPreview
import com.tmdb.designsystem.R
import kotlin.math.roundToInt
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(val id: Int, val name: String, val posterPath: String)

@Composable
fun MovieDetailsScreen(
    id: Int,
    name: String,
    posterPath: String,
    onEvent: (MovieDetailsEvent) -> Unit
) {

    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsContent(
        id = id,
        name = name,
        posterPath = posterPath,
        uiState = uiState,
        onEvent = onEvent
    )
}

@Composable
internal fun MovieDetailsContent(
    id: Int,
    name: String,
    posterPath: String,
    uiState: MovieDetailsUIState,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent(MovieDetailsEvent.NavigateUp) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { onEvent(MovieDetailsEvent.OnToggleFavorite(uiState.details!!)) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_favorites),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        if (uiState.details != null) {
            LazyColumn {
                item {
                    HeadSection(
                        posterPath = posterPath,
                        voteAverage = uiState.details.voteAverage,
                        originalTitle = uiState.details.originalTitle,
                        releaseDate = uiState.details.releaseDate
                    )
                }

            }
        }

    }
}

@Composable
private fun HeadSection(
    posterPath: String,
    voteAverage: Float,
    originalTitle: String,
    releaseDate: String
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        Image(
            painter = networkImagePainter(posterPath),
            contentDescription = "poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(375f / 450f)
        )
        InnerBottomShadow()
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp)
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
                    color = White
                )
                Text(
                    text = releaseDate,
                    style = MaterialTheme.typography.bodyLarge,
                    color = White
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
            .height(150.dp)
            .background(shadowGradient)
    )
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
            color = White
        )
    }
}


@ThemePreviews
@Composable
private fun MovieDetailsContentPreview() {
    AppPreviewWrapper {
        MovieDetailsContent(
            id = 0,
            name = "Movie",
            posterPath = "",
            uiState = MovieDetailsUIState(details = MovieDetailsPreview),
            onEvent = {}
        )
    }
}