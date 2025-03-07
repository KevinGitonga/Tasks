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
package com.app.tasks.core.datepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppStandardIconButton
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A custom composable representing a dialog that displays the time picker dialog.
 *
 * @param initialHour The initial hour to display.
 * @param initialMinute The initial minute to display.
 * @param onTimeSelect The callback to be invoked when a new time is selected.
 * @param onDismissRequest The callback to be invoked when a time has been selected.
 * @param is24Hour Indicates if the time selector should use a 24 hour format or a 12 hour format
 * with AM/PM.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongMethod")
@Composable
fun TasksAppTimePickerDialog(
    onTimeSelect: (hour: Int, minute: Int) -> Unit,
    onDismissRequest: () -> Unit,
    timePickerState: TimePickerState,
) {
    var showTimeInput by remember { mutableStateOf(false) }
    TimePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TasksAppTextButton(
                modifier = Modifier.testTag(tag = "AcceptAlertButton"),
                label = stringResource(id = R.string.ok),
                onClick = {
                    onTimeSelect(timePickerState.hour, timePickerState.minute)
                },
            )
        },
        dismissButton = {
            TasksAppTextButton(
                modifier = Modifier.testTag(tag = "DismissAlertButton"),
                label = stringResource(id = R.string.cancel),
                onClick = onDismissRequest,
            )
        },
        inputToggleButton = {
            TasksAppStandardIconButton(
                vectorIconRes = R.drawable.ic_chevron_down,
                contentDescription =
                    stringResource(
                        // TODO: Get our own string for this (BIT-1405)
                        id = R.string.time,
                    ),
                onClick = { showTimeInput = !showTimeInput },
            )
        },
    ) {
        val modifier = Modifier.weight(1f)
        if (showTimeInput) {
            TimeInput(
                state = timePickerState,
                colors = tasksAppTimePickerColors(),
                modifier = modifier,
            )
        } else {
            TimePicker(
                state = timePickerState,
                colors = tasksAppTimePickerColors(),
                modifier = modifier,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun tasksAppTimePickerColors(): TimePickerColors =
    TimePickerColors(
        clockDialColor = TasksAppTheme.colorScheme.filledButton.backgroundReversed,
        selectorColor = TasksAppTheme.colorScheme.filledButton.background,
        containerColor = TasksAppTheme.colorScheme.filledButton.foreground,
        clockDialSelectedContentColor = TasksAppTheme.colorScheme.background.secondary,
        clockDialUnselectedContentColor = TasksAppTheme.colorScheme.text.primary,
        periodSelectorBorderColor = TasksAppTheme.colorScheme.stroke.divider,
        periodSelectorSelectedContainerColor =
            TasksAppTheme
                .colorScheme
                .filledButton
                .backgroundReversed,
        periodSelectorUnselectedContainerColor = TasksAppTheme.colorScheme.background.primary,
        periodSelectorSelectedContentColor = TasksAppTheme.colorScheme.filledButton.foregroundReversed,
        periodSelectorUnselectedContentColor = TasksAppTheme.colorScheme.text.secondary,
        timeSelectorSelectedContainerColor = TasksAppTheme.colorScheme.background.tertiary,
        timeSelectorUnselectedContainerColor = TasksAppTheme.colorScheme.background.secondary,
        timeSelectorSelectedContentColor = TasksAppTheme.colorScheme.text.primary,
        timeSelectorUnselectedContentColor = TasksAppTheme.colorScheme.text.primary,
    )

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    inputToggleButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = TasksAppTheme.shapes.dialog,
            color = TasksAppTheme.colorScheme.background.primary,
            contentColor = TasksAppTheme.colorScheme.text.primary,
            modifier =
                Modifier
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "AlertPopup"
                    }
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier =
                        Modifier
                            .testTag("AlertTitleText")
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                    text = stringResource(id = R.string.time),
                    color = TasksAppTheme.colorScheme.text.secondary,
                    style = TasksAppTheme.typography.labelMedium,
                )

                content()

                Row(modifier = Modifier.fillMaxWidth()) {
                    inputToggleButton()
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton()
                    Spacer(modifier = Modifier.width(8.dp))
                    confirmButton()
                }
            }
        }
    }
}
