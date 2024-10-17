package com.tmdb.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun networkImagePainter(
    url: String?,
): Painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .size(coil.size.Size.ORIGINAL)
        .build(),
)