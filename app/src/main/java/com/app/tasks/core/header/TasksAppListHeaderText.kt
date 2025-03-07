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
package com.app.tasks.core.header

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp-styled label text.
 *
 * @param label The text content for the label.
 * @param supportingLabel The optional text for the supporting label.
 * @param modifier The [Modifier] to be applied to the label.
 */
@Composable
fun TasksAppListHeaderText(
    label: String,
    supportingLabel: String? = null,
    modifier: Modifier = Modifier,
) {
    val supportLabel = supportingLabel?.let { " ($it)" }.orEmpty()
    Text(
        text = "${label.uppercase()}$supportLabel",
        style = TasksAppTheme.typography.eyebrowMedium,
        color = TasksAppTheme.colorScheme.text.secondary,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun TasksAppListHeaderText_preview() {
    TasksAppTheme {
        Column {
            TasksAppListHeaderText(
                label = "Sample Label",
                modifier = Modifier,
            )
            TasksAppListHeaderText(
                label = "Sample Label",
                supportingLabel = "4",
                modifier = Modifier,
            )
        }
    }
}
