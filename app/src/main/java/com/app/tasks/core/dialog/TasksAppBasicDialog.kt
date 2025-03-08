/*
 * Copyright 2025 Kevin Gitonga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.tasks.core.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp-styled dialog.
 *
 * @param title The optional title to be displayed by the dialog.
 * @param message The message to be displayed under the [title] by the dialog.
 * @param onDismissRequest A lambda that is invoked when the user has requested to dismiss the
 * dialog, whether by tapping "OK", tapping outside the dialog, or pressing the back button.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TasksAppBasicDialog(
    title: String?,
    message: String,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TasksAppTextButton(
                label = stringResource(id = R.string.ok),
                onClick = onDismissRequest,
                modifier = Modifier.testTag(tag = "AcceptAlertButton"),
            )
        },
        title =
            title?.let {
                {
                    Text(
                        text = it,
                        style = TasksAppTheme.typography.headlineSmall,
                        modifier = Modifier.testTag(tag = "AlertTitleText"),
                    )
                }
            },
        text = {
            Text(
                text = message,
                style = TasksAppTheme.typography.bodyMedium,
                modifier = Modifier.testTag(tag = "AlertContentText"),
            )
        },
        shape = TasksAppTheme.shapes.dialog,
        containerColor = TasksAppTheme.colorScheme.background.primary,
        iconContentColor = TasksAppTheme.colorScheme.icon.secondary,
        titleContentColor = TasksAppTheme.colorScheme.text.primary,
        textContentColor = TasksAppTheme.colorScheme.text.primary,
        modifier =
            Modifier.semantics {
                testTagsAsResourceId = true
                testTag = "AlertPopup"
            },
    )
}

@Preview
@Composable
private fun SoccerScoresBasicDialog_preview() {
    TasksAppTheme {
        TasksAppBasicDialog(
            title = "An error has occurred.",
            message = "Username or password is incorrect. Try again.",
            onDismissRequest = {},
        )
    }
}
