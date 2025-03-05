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
package com.app.tasks.core.fab

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp-styled [FloatingActionButton].
 *
 * @param onClick The callback when the button is clicked.
 * @param painter The icon for the button.
 * @param contentDescription The content description for the button.
 * @param modifier The [Modifier] to be applied to the button.
 * @param windowInsets The insets to be applied to this composable.
 */
@Composable
fun TasksAppFloatingActionButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets.displayCutout.union(WindowInsets.navigationBars),
) {
    FloatingActionButton(
        containerColor = TasksAppTheme.colorScheme.filledButton.background,
        contentColor = TasksAppTheme.colorScheme.filledButton.foreground,
        onClick = onClick,
        shape = TasksAppTheme.shapes.fab,
        modifier = modifier.windowInsetsPadding(insets = windowInsets),
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}
