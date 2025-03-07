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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.utils.toFormattedPattern
import com.app.tasks.modules.taskdetails.TaskPriorities
import com.app.tasks.ui.theme.TasksAppTheme
import java.time.ZonedDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListItem(
    taskEntity: TaskEntity,
    onClick: (TaskEntity) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 60.dp)
                .padding(start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(containerColor = TasksAppTheme.colorScheme.background.scrim),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(bottomEnd = 0.dp, bottomStart = 0.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        enabled = true,
                        onClick = { onClick(taskEntity) },
                    ).background(TasksAppTheme.colorScheme.background.primary),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight().padding(5.dp),
            ) {
                Text(
                    text = taskEntity.title,
                    style = TasksAppTheme.typography.bodyLarge,
                    color = TasksAppTheme.colorScheme.text.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp),
                )

                Text(
                    text = taskEntity.description,
                    style = TasksAppTheme.typography.bodyMedium,
                    color = TasksAppTheme.colorScheme.text.secondary,
                    modifier = Modifier.padding(top = 2.dp),
                )

                Text(
                    text = "Due Date: ${taskEntity.dueDate.toFormattedPattern("M/d/yyyy")}",
                    style = TasksAppTheme.typography.bodyMedium,
                    color = TasksAppTheme.colorScheme.text.secondary,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskListItem_Preview() {
    TasksAppTheme {
        TaskListItem(
            taskEntity =
                TaskEntity(
                    title = "Practice DSA challenge",
                    description = "Watch and try implementing new challenge",
                    taskStatus = "pending",
                    dueDate = ZonedDateTime.now().plusDays(3),
                    taskId = 11,
                    priority = TaskPriorities.Low.priorityValue,
                ),
            onClick = {},
        )
    }
}
