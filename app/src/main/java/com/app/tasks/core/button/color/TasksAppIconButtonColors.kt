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

import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Provides a default set of SoccerScores-styled colors for a filled icon button.
 */
@Composable
fun soccerScoresFilledIconButtonColors(): IconButtonColors =
    IconButtonColors(
        containerColor = TasksAppTheme.colorScheme.filledButton.background,
        contentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        disabledContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        disabledContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
    )

/**
 * Provides a default set of SoccerScores-styled colors for a standard icon button.
 */
@Composable
fun soccerScoresStandardIconButtonColors(contentColor: Color = TasksAppTheme.colorScheme.icon.primary): IconButtonColors =
    IconButtonColors(
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
    )

/**
 * Provides a default set of SoccerScores-styled colors for a filled icon button.
 */
@Composable
fun soccerScoresTonalIconButtonColors(): IconButtonColors =
    IconButtonColors(
        containerColor = TasksAppTheme.colorScheme.background.tertiary,
        contentColor = TasksAppTheme.colorScheme.filledButton.foregroundReversed,
        disabledContainerColor = TasksAppTheme.colorScheme.filledButton.backgroundDisabled,
        disabledContentColor = TasksAppTheme.colorScheme.filledButton.foregroundDisabled,
    )
