package com.tmdb.designsystem.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews

@Composable
fun AppFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilterChip(
        modifier = modifier.height(31.dp),
        selected = selected,
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.tertiary,
            selectedLabelColor = MaterialTheme.colorScheme.onTertiary
        ),
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(text = label)
        }
    )
}

@Composable
fun AppChip(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    SuggestionChip(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        label = {
            Text(label)
        }
    )
}

class ChipPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )

}

@ThemePreviews
@Composable
private fun AppFilterChipPreview(
    @PreviewParameter(ChipPreviewParameterProvider::class) selected: Boolean
) {
    AppPreviewWrapper {
        AppFilterChip(
            selected = selected,
            label = "Popular",
            onClick = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}

@ThemePreviews
@Composable
private fun AppChipPreview() {
    AppPreviewWrapper {
        AppChip(
            label = "Popular",
            onClick = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}