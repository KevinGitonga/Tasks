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
package com.app.tasks.core.field.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A composable function to display a row of actions.
 *
 * This function takes in a trailing lambda which provides a `RowScope` in order to
 * layout individual actions. The actions will be arranged in a horizontal
 * sequence, spaced by 8.dp, and are vertically centered.
 *
 * @param actions The composable actions to execute within the [RowScope]. Typically used to
 * layout individual icons or buttons.
 */
@Composable
fun TasksAppRowOfActions(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        content = actions,
    )
}

@Preview(showBackground = true)
@Composable
private fun TasksAppRowOfIconButtons_preview() {
    TasksAppTheme {
        TasksAppRowOfActions {
            Icon(
                painter = rememberVectorPainter(id = R.drawable.ic_back),
                contentDescription = "Icon 1",
                modifier = Modifier.size(24.dp),
            )
            Icon(
                painter = rememberVectorPainter(id = R.drawable.ic_back),
                contentDescription = "Icon 2",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}
