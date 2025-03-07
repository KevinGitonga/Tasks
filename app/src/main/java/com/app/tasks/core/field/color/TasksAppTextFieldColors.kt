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
package com.app.tasks.core.field.color

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Provides a default set of TaskApp-styled colors for a read-only text field button.
 */
@Composable
fun tasksAppTextFieldButtonColors(): TextFieldColors =
    tasksAppTextFieldColors(
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = Color.Transparent,
        disabledTextColor = TasksAppTheme.colorScheme.text.primary,
        disabledBorderColor = Color.Transparent,
        disabledLeadingIconColor = TasksAppTheme.colorScheme.icon.primary,
        disabledTrailingIconColor = TasksAppTheme.colorScheme.icon.primary,
        disabledLabelColor = TasksAppTheme.colorScheme.text.secondary,
        disabledPlaceholderColor = TasksAppTheme.colorScheme.text.secondary,
        disabledSupportingTextColor = TasksAppTheme.colorScheme.text.secondary,
    )

/**
 * Provides a default set of TaskApp-styled colors for text fields.
 */
@Composable
fun tasksAppTextFieldColors(
    focusedBorderColor: Color = Color.Transparent,
    unfocusedBorderColor: Color = Color.Transparent,
    disabledTextColor: Color = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
    disabledBorderColor: Color = Color.Transparent,
    disabledLeadingIconColor: Color = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
    disabledTrailingIconColor: Color = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
    disabledLabelColor: Color = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
    disabledPlaceholderColor: Color = TasksAppTheme.colorScheme.text.secondary,
    disabledSupportingTextColor: Color =
        TasksAppTheme
            .colorScheme
            .outlineButton
            .foregroundDisabled,
): TextFieldColors =
    TextFieldColors(
        focusedTextColor = TasksAppTheme.colorScheme.text.primary,
        unfocusedTextColor = TasksAppTheme.colorScheme.text.primary,
        disabledTextColor = disabledTextColor,
        errorTextColor = TasksAppTheme.colorScheme.text.primary,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        cursorColor = TasksAppTheme.colorScheme.text.interaction,
        errorCursorColor = TasksAppTheme.colorScheme.text.interaction,
        textSelectionColors =
            TextSelectionColors(
                handleColor = TasksAppTheme.colorScheme.stroke.border,
                backgroundColor = TasksAppTheme.colorScheme.stroke.border.copy(alpha = 0.4f),
            ),
        focusedIndicatorColor = focusedBorderColor,
        unfocusedIndicatorColor = unfocusedBorderColor,
        disabledIndicatorColor = disabledBorderColor,
        errorIndicatorColor = TasksAppTheme.colorScheme.status.error,
        focusedLeadingIconColor = TasksAppTheme.colorScheme.icon.primary,
        unfocusedLeadingIconColor = TasksAppTheme.colorScheme.icon.primary,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = TasksAppTheme.colorScheme.icon.primary,
        focusedTrailingIconColor = TasksAppTheme.colorScheme.icon.primary,
        unfocusedTrailingIconColor = TasksAppTheme.colorScheme.icon.primary,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = TasksAppTheme.colorScheme.status.error,
        focusedLabelColor = TasksAppTheme.colorScheme.text.secondary,
        unfocusedLabelColor = TasksAppTheme.colorScheme.text.secondary,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = TasksAppTheme.colorScheme.status.error,
        focusedPlaceholderColor = TasksAppTheme.colorScheme.text.secondary,
        unfocusedPlaceholderColor = TasksAppTheme.colorScheme.text.secondary,
        disabledPlaceholderColor = disabledPlaceholderColor,
        errorPlaceholderColor = TasksAppTheme.colorScheme.text.secondary,
        focusedSupportingTextColor = TasksAppTheme.colorScheme.text.secondary,
        unfocusedSupportingTextColor = TasksAppTheme.colorScheme.text.secondary,
        disabledSupportingTextColor = disabledSupportingTextColor,
        errorSupportingTextColor = TasksAppTheme.colorScheme.text.secondary,
        focusedPrefixColor = TasksAppTheme.colorScheme.text.secondary,
        unfocusedPrefixColor = TasksAppTheme.colorScheme.text.secondary,
        disabledPrefixColor = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
        errorPrefixColor = TasksAppTheme.colorScheme.status.error,
        focusedSuffixColor = TasksAppTheme.colorScheme.text.secondary,
        unfocusedSuffixColor = TasksAppTheme.colorScheme.text.secondary,
        disabledSuffixColor = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
        errorSuffixColor = TasksAppTheme.colorScheme.status.error,
    )
