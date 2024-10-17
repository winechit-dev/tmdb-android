@file:OptIn(ExperimentalFoundationApi::class)

package com.tmdb.discover.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.designsystem.components.AppFilterChip
import com.tmdb.designsystem.components.AppSearchBar
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.LocalEntryPadding
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.discover.genresPreview
import com.tmdb.discover.model.GenreUIModel
import com.tmdb.discover.moviesPreview
import com.tmdb.discover.subtitle_discover
import com.tmdb.discover.title_discover
import com.tmdb.ui.MovieItem
import com.tmdb.ui.MovieUIModel
import kotlinx.serialization.Serializable

@Serializable
data object Discover

@Composable
fun DiscoverScreen() {
    val viewModel: DiscoverViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    DiscoverContent(
        uiState = uiState
    )
}

@Composable
internal fun DiscoverContent(
    modifier: Modifier = Modifier,
    uiState: DiscoverUIState
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(vertical = 30.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .padding(LocalEntryPadding.current)
                .padding(top = 30.dp)
        ) {
            item {
                Text(
                    text = title_discover,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                )
                Text(
                    text = subtitle_discover,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 6.dp)
                )
            }
            stickyHeader {
                AppSearchBar(
                    query = "",
                    onQueryChanged = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                )
            }

            genresSection(
                genres = uiState.genres,
                onClickItem = {}
            )

            moviesSection(
                title = "Today Trending Movies",
                movies = uiState.trendingTodayMovies,
                onClickItem = {}
            )
        }
    }
}

private fun LazyListScope.moviesSection(
    title: String,
    movies: List<MovieUIModel>,
    onClickItem: (MovieUIModel) -> Unit
) {
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
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) {
            items(
                items = movies,
                key = { it.id },
                contentType = { "Movie" }
            ) { model ->
                MovieItem(
                    model = model,
                    onClick = onClickItem
                )
            }
        }
    }
}

private fun LazyListScope.genresSection(
    genres: List<GenreUIModel>,
    onClickItem: (GenreUIModel) -> Unit
) {
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) {
            items(
                items = genres,
                key = { it.id },
                contentType = { "Genre" }
            ) { genre ->
                AppFilterChip(
                    selected = genre.selected,
                    label = genre.name,
                    onClick = { onClickItem(genre) }
                )
            }
        }
    }
}


@ThemePreviews
@Composable
internal fun DiscoverContentPreview() {
    AppPreviewWrapper {
        DiscoverContent(
            uiState = DiscoverUIState(
                genres = genresPreview,
                trendingTodayMovies = moviesPreview
            )
        )
    }
}