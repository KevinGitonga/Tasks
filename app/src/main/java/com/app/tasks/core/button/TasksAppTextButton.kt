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
package com.app.tasks.core.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.core.button.color.tasksAppTextButtonColors
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp-styled [TextButton].
 *
 * @param label The label for the button.
 * @param onClick The callback when the button is clicked.
 * @param modifier The [Modifier] to be applied to the button.
 * @param icon The icon for the button.
 * @param labelTextColor The color for the label text.
 */
@Composable
fun TasksAppTextButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    isEnabled: Boolean = true,
    labelTextColor: Color = TasksAppTheme.colorScheme.outlineButton.foreground,
) {
    TextButton(
        modifier = modifier.semantics(mergeDescendants = true) {},
        onClick = onClick,
        enabled = isEnabled,
        contentPadding =
            PaddingValues(
                top = 10.dp,
                bottom = 10.dp,
                start = 12.dp,
                end = if (icon == null) 12.dp else 16.dp,
            ),
        colors = tasksAppTextButtonColors(contentColor = labelTextColor),
    ) {
        icon?.let {
            Icon(
                painter = icon,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = label,
            style = TasksAppTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
private fun TasksAppTextButton_preview() {
    Column {
        TasksAppTextButton(
            label = "Label",
            onClick = {},
            isEnabled = true,
        )
        TasksAppTextButton(
            label = "Label",
            onClick = {},
            isEnabled = false,
        )
    }
}
