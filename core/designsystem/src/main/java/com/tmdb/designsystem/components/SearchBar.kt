package com.tmdb.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tmdb.designsystem.R
import com.tmdb.designsystem.components.textfield.core.CoreTextField
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews

@Composable
fun AppSearchBar(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    readOnly: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant

    CoreTextField(
        value = query,
        onValueChange = onQueryChanged,
        readOnly = readOnly,
        placeholder = {
            Text(text = "Search")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
            autoCorrectEnabled = true
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "ic_search",
            )
        },
        shape = CircleShape,
        enabled = true,
        isError = false,
        modifier = modifier
            .focusRequester(focusRequester)
            .height(46.dp)
    )
}

@ThemePreviews
@Composable
private fun AppSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    AppPreviewWrapper {
        AppSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            query = query,
            onQueryChanged = { query = it }
        )
    }
}