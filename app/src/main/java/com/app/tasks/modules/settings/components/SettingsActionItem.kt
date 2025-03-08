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
package com.app.tasks.modules.settings.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppStandardIconButton

/**
 * Represents the TasksApp settings action item.
 *
 * This is an [Icon] composable tailored specifically for the search functionality
 * in the TasksApp.
 * It presents the search icon and offers an `onClick` callback for when the icon is tapped.
 *
 * @param contentDescription A description of the UI element, used for accessibility purposes.
 * @param onClick A callback to be invoked when this action item is clicked.
 */
@Composable
fun SettingsActionItem(
    contentDescription: String,
    onClick: () -> Unit,
) {
    TasksAppStandardIconButton(
        vectorIconRes = R.drawable.ic_settings,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = Modifier.testTag(tag = "SettingsButton"),
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsActionItem_preview() {
    SettingsActionItem(
        contentDescription = "FilterButton",
        onClick = {},
    )
}
