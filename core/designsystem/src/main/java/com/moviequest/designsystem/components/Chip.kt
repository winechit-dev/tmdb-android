package com.moviequest.designsystem.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.moviequest.designsystem.theme.AppPreview

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
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(text = label)
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

@Preview
@Composable
private fun AppFilterChipPreview(
    @PreviewParameter(ChipPreviewParameterProvider::class) selected: Boolean
) {
    AppPreview {
        AppFilterChip(
            selected = selected,
            label = "Popular",
            onClick = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}