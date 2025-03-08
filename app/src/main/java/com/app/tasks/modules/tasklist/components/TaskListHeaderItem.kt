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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppStandardIconButton
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.extensions.capitalize
import com.app.tasks.core.extensions.cardStyle
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.modules.tasklist.models.TaskHeaderItemModel
import com.app.tasks.ui.theme.TasksAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListHeaderItem(
    taskHeaderItemModel: TaskHeaderItemModel,
    onClick: (() -> Unit)? = null,
    isExpanded: Boolean,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = TasksAppTheme.colorScheme.background.scrim),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        enabled = true,
                        onClick = { onClick?.invoke() },
                    ).background(TasksAppTheme.colorScheme.background.primary)
                    .cardStyle(
                        cardStyle = CardStyle.Middle(hasDivider = true),
                        onClick = onClick,
                        paddingHorizontal = 10.dp,
                    ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${taskHeaderItemModel.sectionName.capitalize()}(${taskHeaderItemModel.totalItemsCount})",
                style = TasksAppTheme.typography.labelLarge,
                color = TasksAppTheme.colorScheme.text.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                    Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .weight(1.1f),
            )

            val rotation by animateFloatAsState(
                targetValue = if (isExpanded) 0f else -180f,
                animationSpec = tween(500),
            )

            TasksAppStandardIconButton(
                painter = rememberVectorPainter(id = R.drawable.ic_chevron_down),
                modifier =
                    Modifier
                        .rotate(rotation)
                        .padding(start = 10.dp, end = 10.dp)
                        .weight(0.2f),
                contentDescription = stringResource(R.string.expand),
                onClick = {
                    onClick?.invoke()
                },
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = TasksAppTheme.colorScheme.stroke.divider,
        )
    }
}

@Preview
@Composable
fun PreviewMatchListHeaderItem() {
    TasksAppTheme {
        TaskListHeaderItem(
            taskHeaderItemModel =
                TaskHeaderItemModel(
                    sectionName = "pending",
                    totalItemsCount = 1,
                    isExpanded = true,
                ),
            onClick = {},
            isExpanded = true,
        )
    }
}
