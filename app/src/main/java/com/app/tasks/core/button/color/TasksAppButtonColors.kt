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
package com.app.tasks.core.button.color

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.tasks.core.button.SoccerScoresOutlinedButtonColors
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Provides a default set of Soccer-styled colors for a filled button.
 */
@Composable
fun tasksAppFilledButtonColors(): ButtonColors =
    ButtonColors(
        containerColor = TasksAppTheme.colorScheme.filledButton.background,
        contentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        disabledContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        disabledContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
    )

/**
 * Provides a default set of Soccer-styled colors for a filled error button.
 */
@Composable
fun tasksAppFilledErrorButtonColors() =
    ButtonColors(
        containerColor = TasksAppTheme.colorScheme.status.weak1,
        contentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        disabledContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        disabledContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
    )

/**
 * Provides a default set of Soccer-styled colors for an outlined button.
 */
@Composable
fun tasksAppOutlinedButtonColors(
    contentColor: Color = TasksAppTheme.colorScheme.outlineButton.foreground,
    outlineColor: Color = TasksAppTheme.colorScheme.outlineButton.border,
    outlineColorDisabled: Color = TasksAppTheme.colorScheme.outlineButton.borderDisabled,
): SoccerScoresOutlinedButtonColors =
    SoccerScoresOutlinedButtonColors(
        materialButtonColors =
            ButtonColors(
                containerColor = Color.Transparent,
                contentColor = contentColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
            ),
        outlineBorderColor = outlineColor,
        outlinedDisabledBorderColor = outlineColorDisabled,
    )

/**
 * Provides a default set of Soccer-styled colors for a text button.
 */
@Composable
fun tasksAppTextButtonColors(contentColor: Color = TasksAppTheme.colorScheme.outlineButton.foreground): ButtonColors =
    ButtonColors(
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = TasksAppTheme.colorScheme.outlineButton.foregroundDisabled,
    )
