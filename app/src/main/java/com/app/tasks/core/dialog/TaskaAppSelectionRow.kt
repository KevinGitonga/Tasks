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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.app.tasks.core.radio.TasksAppRadioButton
import com.app.tasks.core.utils.Text
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A clickable item that displays a radio button and text.
 *
 * @param text The text to display.
 * @param onClick Invoked when either the radio button or text is clicked.
 * @param isSelected Whether or not the radio button should be checked.
 */
@Composable
fun TasksAppSelectionRow(
    text: Text,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .testTag("AlertRadioButtonOption")
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                        ripple(
                            color = TasksAppTheme.colorScheme.background.pressed,
                        ),
                    onClick = onClick,
                )
                .semantics(mergeDescendants = true) {
                    selected = isSelected
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TasksAppRadioButton(
            modifier = Modifier.padding(16.dp),
            isSelected = isSelected,
            onClick = null,
        )
        Text(
            text = text(),
            color = TasksAppTheme.colorScheme.text.primary,
            style = TasksAppTheme.typography.bodyLarge,
            modifier = Modifier.testTag("AlertRadioButtonOptionName"),
        )
    }
}
