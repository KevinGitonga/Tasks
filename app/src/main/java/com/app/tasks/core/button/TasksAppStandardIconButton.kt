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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.app.tasks.R
import com.app.tasks.core.button.color.soccerScoresStandardIconButtonColors
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A standard icon button that displays an icon.
 *
 * @param vectorIconRes Icon to display on the button.
 * @param contentDescription The content description for this icon button.
 * @param onClick Callback for when the icon button is clicked.
 * @param modifier A [Modifier] for the composable.
 * @param isEnabled Whether or not the button should be enabled.
 */
@Composable
fun TasksAppStandardIconButton(
    @DrawableRes vectorIconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    contentColor: Color = TasksAppTheme.colorScheme.icon.primary,
) {
    TasksAppStandardIconButton(
        painter = rememberVectorPainter(id = vectorIconRes),
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        isEnabled = isEnabled,
        contentColor = contentColor,
    )
}

/**
 * A standard icon button that displays an icon.
 *
 * @param painter Painter icon to display on the button.
 * @param contentDescription The content description for this icon button.
 * @param onClick Callback for when the icon button is clicked.
 * @param modifier A [Modifier] for the composable.
 * @param isEnabled Whether or not the button should be enabled.
 */
@Composable
fun TasksAppStandardIconButton(
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    contentColor: Color = TasksAppTheme.colorScheme.icon.primary,
) {
    IconButton(
        modifier =
            modifier.semantics(mergeDescendants = true) {
                this.contentDescription = contentDescription
            },
        onClick = onClick,
        colors = soccerScoresStandardIconButtonColors(contentColor = contentColor),
        enabled = isEnabled,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}

/**
 * A standard icon button that displays a badged icon.
 *
 * @param vectorIconRes Icon resource Id.
 * @param contentDescription The content description for this icon button.
 * @param badgeText The text content for the badge.
 * @param onClick Callback for when the icon button is clicked.
 * @param modifier A [Modifier] for the composable.
 * @param isEnabled Whether or not the button should be enabled.
 */
@Composable
fun SoccerScoresStandardBadgedIconButton(
    @DrawableRes vectorIconRes: Int,
    contentDescription: String,
    badgeText: String,
    isVisible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    contentColor: Color = TasksAppTheme.colorScheme.icon.primary,
) {
    IconButton(
        modifier =
            modifier.semantics(mergeDescendants = true) {
                this.contentDescription = contentDescription
            },
        onClick = onClick,
        colors = soccerScoresStandardIconButtonColors(contentColor = contentColor),
        enabled = isEnabled,
    ) {
        Icon(
            painter = rememberVectorPainter(vectorIconRes),
            contentDescription = contentDescription,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoccerScoresStandardIconButton_preview() {
    TasksAppTheme {
        TasksAppStandardIconButton(
            vectorIconRes = R.drawable.ic_back,
            contentDescription = "Sample Icon",
            onClick = {},
        )
    }
}
