import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.R
import com.tmdb.designsystem.components.AppButton
import com.tmdb.designsystem.components.AppChip
import com.tmdb.designsystem.components.AppFilterChip
import com.tmdb.designsystem.components.AppSearchBar
import com.tmdb.designsystem.components.ButtonPreviewParameterProvider
import com.tmdb.designsystem.components.ChipPreviewParameterProvider
import com.tmdb.designsystem.theme.AppPreviewWithSharedTransitionLayout
import com.tmdb.designsystem.theme.AppPreviewWrapper

@Preview
@Composable
private fun ButtonPreview(
    @PreviewParameter(ButtonPreviewParameterProvider::class) enabled: Boolean
) {
    AppPreviewWrapper {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier.padding(30.dp)
        ) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                text = "Click Here",
                onClick = {}
            )
        }
    }
}


@Preview
@Composable
private fun AppSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    AppPreviewWithSharedTransitionLayout {
        AppSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            query = query,
            onQueryChanged = { query = it },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "ic_search",
                )
            }
        )
    }
}

@Preview
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

@Preview
@Composable
private fun AppChipPreview() {
    AppPreviewWrapper {
        AppChip(
            label = "Popular",
            modifier = Modifier.padding(20.dp)
        )
    }
}