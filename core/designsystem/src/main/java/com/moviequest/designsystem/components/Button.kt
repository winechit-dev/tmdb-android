package com.moviequest.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.moviequest.designsystem.R
import com.moviequest.designsystem.theme.AppPreview
import com.moviequest.designsystem.theme.ThemePreviews
import com.moviequest.designsystem.theme.asBrush
import com.moviequest.designsystem.theme.defaultButton

val MinHeight = 43.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    @DrawableRes leadingIcon: Int? = null,
    textStyle: TextStyle = MaterialTheme.typography.defaultButton,
    onClick: () -> Unit
) {

    val disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f).asBrush()
    val containerColor = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.primary
        )
    )
    val brush = remember(enabled) {
        if (enabled) containerColor else disabledContainerColor
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .background(brush = brush, shape = CircleShape)
            .defaultMinSize(minHeight = MinHeight),
        enabled = enabled,
        shape = CircleShape,
        colors = defaultButtonColors(),
        content = {
            ProvideTextStyle(value = textStyle) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            painter = painterResource(leadingIcon),
                            contentDescription = "leadingIcon"
                        )
                    }
                    Text(text = text)
                }
            }
        }
    )
}

@Composable
fun defaultButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent
)

class ButtonPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )

}

@ThemePreviews
@Composable
private fun ButtonPreview(
    @PreviewParameter(ButtonPreviewParameterProvider::class) enabled: Boolean
) {
    AppPreview {
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

@ThemePreviews
@Composable
private fun ButtonWithLeadingIconPreview(
    @PreviewParameter(ButtonPreviewParameterProvider::class) enabled: Boolean
) {
    AppPreview {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier.padding(30.dp)
        ) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                text = "Click Here",
                leadingIcon = R.drawable.ic_play,
                onClick = {}
            )
        }
    }
}