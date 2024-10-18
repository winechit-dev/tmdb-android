@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.designsystem.utils

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.spring

data class AppSharedElementKey(
    val id: String,
    val type: AppSharedElementType
)

enum class AppSharedElementType {
    Bounds
}


val detailBoundsTransform = BoundsTransform { _, _ ->
    spatialExpressiveSpring()
}

fun <T> spatialExpressiveSpring() = spring<T>(
    dampingRatio = 0.8f,
    stiffness = 380f
)

fun <T> nonSpatialExpressiveSpring() = spring<T>(
    dampingRatio = 1f,
    stiffness = 1600f
)

