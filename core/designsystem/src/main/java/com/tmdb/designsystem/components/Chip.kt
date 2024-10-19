package com.tmdb.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.bounceClick

@Composable
fun AppFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilterChip(
        modifier = modifier
            .bounceClick(enabled = enabled)
            .height(31.dp),
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
    label: String
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                vertical = 8.dp,
                horizontal = 14.dp
            )

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
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
            modifier = Modifier.padding(20.dp)
        )
    }
}