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

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.core.extensions.orNow
import com.app.tasks.core.field.color.tasksAppTextFieldColors
import com.app.tasks.ui.theme.TasksAppTheme
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksDatePicker(
    onDismiss: () -> Unit,
    currentZonedDateTime: ZonedDateTime?,
    onDateSelect: (ZonedDateTime) -> Unit,
    datePickerState: DatePickerState,
) {
    DatePickerDialog(
        shape = TasksAppTheme.shapes.dialog,
        colors = tasksAppDatePickerColors(),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TasksAppTextButton(
                label = stringResource(id = R.string.ok),
                onClick = {
                    onDateSelect(
                        ZonedDateTime
                            .ofInstant(
                                Instant.ofEpochMilli(
                                    requireNotNull(datePickerState.selectedDateMillis),
                                ),
                                ZoneOffset.systemDefault(),
                            )
                            .withZoneSameLocal(currentZonedDateTime.orNow().zone),
                    )
                    onDismiss()
                },
                modifier = Modifier.testTag(tag = "AcceptAlertButton"),
            )
        },
        dismissButton = {
            TasksAppTextButton(
                label = stringResource(id = R.string.cancel),
                onClick = { onDismiss() },
                modifier = Modifier.testTag(tag = "DismissAlertButton"),
            )
        },
        modifier =
            Modifier.semantics {
                testTag = "AlertPopup"
            },
    ) {
        DatePicker(
            state = datePickerState,
            colors = tasksAppDatePickerColors(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun tasksAppDatePickerColors(): DatePickerColors =
    DatePickerColors(
        containerColor = TasksAppTheme.colorScheme.background.primary,
        titleContentColor = TasksAppTheme.colorScheme.text.secondary,
        headlineContentColor = TasksAppTheme.colorScheme.text.primary,
        weekdayContentColor = TasksAppTheme.colorScheme.text.primary,
        subheadContentColor = TasksAppTheme.colorScheme.text.secondary,
        navigationContentColor = TasksAppTheme.colorScheme.icon.primary,
        yearContentColor = TasksAppTheme.colorScheme.text.primary,
        disabledYearContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
        currentYearContentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        selectedYearContentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        disabledSelectedYearContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
        selectedYearContainerColor = TasksAppTheme.colorScheme.filledButton.background,
        disabledSelectedYearContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        dayContentColor = TasksAppTheme.colorScheme.text.primary,
        disabledDayContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
        selectedDayContentColor = TasksAppTheme.colorScheme.text.reversed,
        disabledSelectedDayContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
        selectedDayContainerColor = TasksAppTheme.colorScheme.filledButton.background,
        disabledSelectedDayContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        todayContentColor = TasksAppTheme.colorScheme.outlineButton.foreground,
        todayDateBorderColor = TasksAppTheme.colorScheme.outlineButton.border,
        dayInSelectionRangeContainerColor = TasksAppTheme.colorScheme.filledButton.background,
        dividerColor = TasksAppTheme.colorScheme.stroke.divider,
        dayInSelectionRangeContentColor = TasksAppTheme.colorScheme.text.primary,
        dateTextFieldColors =
            tasksAppTextFieldColors(
                disabledBorderColor = TasksAppTheme.colorScheme.outlineButton.borderDisabled,
                focusedBorderColor = TasksAppTheme.colorScheme.stroke.border,
                unfocusedBorderColor = TasksAppTheme.colorScheme.stroke.divider,
            ),
    )
