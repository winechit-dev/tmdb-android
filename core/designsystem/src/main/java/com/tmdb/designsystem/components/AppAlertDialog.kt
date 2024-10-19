package com.tmdb.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews

@Composable
fun AppAlertDialog(
    title: String = "",
    body: String = "",
    onDismissRequest: () -> Unit = {},
    confirmButtonText: String = "Okay",
    confirmButton: () -> Unit,
    dismissButtonText: String = "Cancel",
    dismissButton: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        properties = properties,
        title = {
            if (title.isNotBlank()) {
                Text(text = title)
            }
        },
        text = {
            if (body.isNotBlank()) {
                Text(text = body)
            }
        },
        onDismissRequest = onDismissRequest,
        dismissButton = {
            if (dismissButton != null) {
                TextButton(
                    onClick = dismissButton,
                    content = {
                        Text(dismissButtonText)
                    }
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = confirmButton,
                content = {
                    Text(confirmButtonText)
                }
            )
        }
    )
}

@ThemePreviews
@Composable
private fun AppAlertDialogPreview() {
    AppPreviewWrapper {
        AppAlertDialog(
            title = "Error",
            body = "Message",
            confirmButton = { }
        )
    }
}

