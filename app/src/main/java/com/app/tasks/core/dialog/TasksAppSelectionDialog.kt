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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.core.divider.TasksAppHorizontalDivider
import com.app.tasks.core.extensions.maxDialogHeight
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Displays a dialog with a title and "Cancel" button.
 *
 * @param title Title to display.
 * @param onDismissRequest Invoked when the user dismisses the dialog.
 * @param selectionItems Lambda containing selection items to show to the user. See
 * [TasksAppSelectionRow].
 */
@Suppress("LongMethod")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TasksAppSelectionDialog(
    title: String,
    onDismissRequest: () -> Unit,
    selectionItems: @Composable ColumnScope.() -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        val configuration = LocalConfiguration.current
        val scrollState = rememberScrollState()
        Column(
            modifier =
                Modifier
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "AlertPopup"
                    }
                    .requiredHeightIn(
                        max = configuration.maxDialogHeight,
                    )
                    // This background is necessary for the dialog to not be transparent.
                    .background(
                        color = TasksAppTheme.colorScheme.background.primary,
                        shape = TasksAppTheme.shapes.dialog,
                    ),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                modifier =
                    Modifier
                        .testTag("AlertTitleText")
                        .padding(24.dp)
                        .fillMaxWidth(),
                text = title,
                color = TasksAppTheme.colorScheme.text.primary,
                style = TasksAppTheme.typography.headlineSmall,
            )
            if (scrollState.canScrollBackward) {
                TasksAppHorizontalDivider()
            }
            Column(
                modifier =
                    Modifier
                        .weight(1f, fill = false)
                        .verticalScroll(scrollState),
                content = selectionItems,
            )
            if (scrollState.canScrollForward) {
                TasksAppHorizontalDivider()
            }
            TasksAppTextButton(
                modifier =
                    Modifier
                        .testTag("DismissAlertButton")
                        .padding(24.dp),
                label = stringResource(id = R.string.cancel),
                onClick = onDismissRequest,
            )
        }
    }
}
