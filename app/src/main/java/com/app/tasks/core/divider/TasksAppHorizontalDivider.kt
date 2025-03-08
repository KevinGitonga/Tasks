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
package com.app.tasks.core.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A divider line.
 *
 * @param modifier The [Modifier] to be applied to this divider.
 * @param thickness The thickness of this divider. Using [Dp.Hairline] will produce a single pixel
 * divider regardless of screen density.
 * @param color The color of this divider.
 */
@Composable
fun TasksAppHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = (0.5).dp,
    color: Color = TasksAppTheme.colorScheme.stroke.divider,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color,
    )
}
