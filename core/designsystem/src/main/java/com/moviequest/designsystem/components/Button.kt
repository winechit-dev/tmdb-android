package com.moviequest.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.moviequest.designsystem.R
import com.moviequest.designsystem.theme.AppPreview
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
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = MinHeight),
        enabled = enabled,
        shape = defaultButtonShape,
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


val defaultButtonShape: Shape
    get() = RoundedCornerShape(
        topStart = CornerSize(50),
        topEnd = CornerSize(50),
        bottomStart = CornerSize(50),
        bottomEnd = CornerSize(50)
    )


class ButtonPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )

}

@Preview
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

@Preview
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