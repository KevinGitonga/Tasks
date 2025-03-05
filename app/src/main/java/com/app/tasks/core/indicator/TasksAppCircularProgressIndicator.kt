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
package com.app.tasks.core.indicator

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A TasksApp-styled [CircularProgressIndicator].
 */
@Composable
fun TasksAppCircularProgressIndicator(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = TasksAppTheme.colorScheme.stroke.border,
        trackColor = TasksAppTheme.colorScheme.background.tertiary,
    )
}
