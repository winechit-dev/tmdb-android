@file:OptIn(ExperimentalFoundationApi::class)

package com.moviequest.discover.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.moviequest.designsystem.components.AppFilterChip
import com.moviequest.designsystem.components.AppSearchBar
import com.moviequest.designsystem.theme.AppPreviewWrapper
import com.moviequest.designsystem.theme.LocalEntryPadding
import com.moviequest.designsystem.theme.ThemePreviews
import com.moviequest.discover.genresPreview
import com.moviequest.discover.subtitle_discover
import com.moviequest.discover.title_discover
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
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 30.dp)
                ) {
                    items(
                        items = uiState.genres,
                        key = { it.id },
                        contentType = { "Genre" }
                    ) { genre ->
                        AppFilterChip(
                            selected = genre.selected,
                            label = genre.name,
                            onClick = {}
                        )
                    }
                }
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
                genres = genresPreview
            )
        )
    }
}