@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.designsystem.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import com.tmdb.designsystem.theme.AppPreviewWithSharedTransitionLayout
import com.tmdb.designsystem.theme.LocalNavAnimatedVisibilityScope
import com.tmdb.designsystem.theme.LocalSharedTransitionScope
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.designsystem.utils.AppSharedElementKey
import com.tmdb.designsystem.utils.AppSharedElementType
import com.tmdb.designsystem.utils.detailBoundsTransform

@Composable
fun AppSearchBar(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")

    with(sharedTransitionScope) {
        CoreTextField(
            value = query,
            onValueChange = onQueryChanged,
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
            trailingIcon = trailingIcon,
            shape = CircleShape,
            enabled = true,
            isError = false,
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .height(46.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = AppSharedElementKey(
                            id = "",
                            type = AppSharedElementType.SearchBar
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = detailBoundsTransform,
                    enter = fadeIn(),
                    exit = fadeOut()
                )
        )
    }
}

@ThemePreviews
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