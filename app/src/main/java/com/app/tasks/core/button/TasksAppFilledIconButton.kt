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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.button.color.soccerScoresFilledIconButtonColors
import com.app.tasks.core.button.color.tasksAppFilledButtonColors
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp-styled filled [Button].
 *
 * @param label The label for the button.
 * @param onClick The callback when the button is clicked.
 * @param modifier The [Modifier] to be applied to the button.
 * @param icon The icon for the button.
 * @param isEnabled Whether or not the button is enabled.
 */
@Composable
fun TasksAppFilledButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    isEnabled: Boolean = true,
    colors: ButtonColors = tasksAppFilledButtonColors(),
) {
    Button(
        modifier = modifier.semantics(mergeDescendants = true) {},
        onClick = onClick,
        enabled = isEnabled,
        contentPadding =
            PaddingValues(
                top = 10.dp,
                bottom = 10.dp,
                start = if (icon == null) 24.dp else 16.dp,
                end = 24.dp,
            ),
        colors = colors,
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

/**
 * A filled icon button that displays an icon.
 *
 * @param vectorIconRes Icon to display on the button.
 * @param contentDescription The content description for this icon button.
 * @param onClick Callback for when the icon button is clicked.
 * @param modifier A [Modifier] for the composable.
 * @param isEnabled Whether or not the button should be enabled.
 */
@Composable
fun SoccerScoresFilledIconButton(
    @DrawableRes vectorIconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    FilledIconButton(
        modifier =
            modifier.semantics(mergeDescendants = true) {
                this.contentDescription = contentDescription
            },
        onClick = onClick,
        colors = soccerScoresFilledIconButtonColors(),
        enabled = isEnabled,
    ) {
        Icon(
            painter = rememberVectorPainter(id = vectorIconRes),
            contentDescription = contentDescription,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoccerScoresFilledIconButton_preview() {
    TasksAppTheme {
        SoccerScoresFilledIconButton(
            vectorIconRes = R.drawable.ic_back,
            contentDescription = "Sample Icon",
            onClick = {},
        )
    }
}
