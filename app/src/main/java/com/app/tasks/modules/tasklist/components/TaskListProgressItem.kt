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
package com.app.tasks.modules.tasklist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.extensions.cardStyle
import com.app.tasks.core.indicator.TasksAppCircularProgressbar
import com.app.tasks.ui.theme.TasksAppTheme

@Composable
fun TaskListProgressItem(
    progress: Double,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 60.dp)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                .wrapContentHeight()
                .background(TasksAppTheme.colorScheme.background.primary),
        colors = CardDefaults.cardColors(containerColor = TasksAppTheme.colorScheme.background.primary),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 60.dp)
                    .cardStyle(
                        cardStyle = CardStyle.Bottom,
                        onClick = {},
                        paddingHorizontal = 10.dp,
                    ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TasksAppCircularProgressbar(
                dataUsage = progress.toFloat(),
                name = "Progress",
            )
        }
    }
}

@Preview
@Composable
fun PreviewTaskListProgressItem() {
    TasksAppTheme {
        TaskListProgressItem(
            progress = 80.0,
        )
    }
}
