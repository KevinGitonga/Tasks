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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.divider.TasksAppHorizontalDivider
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.extensions.cardStyle
import com.app.tasks.core.extensions.nullableTestTag
import com.app.tasks.core.field.TooltipData
import com.app.tasks.core.field.color.tasksAppTextFieldButtonColors
import com.app.tasks.core.field.toolbar.TasksAppRowOfActions
import com.app.tasks.core.utils.persistentListOfNotNull
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A button which uses a read-only text field for layout and style purposes.
 */
@Suppress("LongMethod")
@Composable
fun TasksAppTextSelectionButton(
    label: String,
    selectedOption: String?,
    onClick: () -> Unit,
    cardStyle: CardStyle?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    supportingText: String? = null,
    tooltip: TooltipData? = null,
    insets: PaddingValues = PaddingValues(),
    textFieldTestTag: String? = null,
    semanticRole: Role = Role.Button,
    actionsPadding: PaddingValues = PaddingValues(end = 4.dp),
    actions: @Composable RowScope.() -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .clearAndSetSemantics {
                    role = semanticRole
                    contentDescription = supportingText
                        ?.let { "$selectedOption. $label. $it" }
                        ?: "$selectedOption. $label"
                    customActions =
                        persistentListOfNotNull(
                            tooltip?.let {
                                CustomAccessibilityAction(
                                    label = it.contentDescription,
                                    action = {
                                        it.onClick()
                                        true
                                    },
                                )
                            },
                        )
                }
                .cardStyle(
                    cardStyle = cardStyle,
                    paddingTop = 6.dp,
                    paddingBottom = 0.dp,
                    onClick = onClick,
                    clickEnabled = enabled,
                )
                .padding(paddingValues = insets),
    ) {
        TextField(
            textStyle = TasksAppTheme.typography.bodyLarge,
            readOnly = true,
            enabled = false,
            label = {
                Row {
                    Text(
                        text = label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    tooltip?.let {
                        Spacer(modifier = Modifier.width(8.dp))
                        TasksAppStandardIconButton(
                            vectorIconRes = R.drawable.ic_back,
                            contentDescription = it.contentDescription,
                            onClick = it.onClick,
                            contentColor = TasksAppTheme.colorScheme.icon.secondary,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
            },
            trailingIcon = {
                TasksAppRowOfActions(
                    modifier = Modifier.padding(paddingValues = actionsPadding),
                    actions = {
                        Icon(
                            painter = rememberVectorPainter(id = R.drawable.ic_chevron_down),
                            contentDescription = null,
                            tint = TasksAppTheme.colorScheme.icon.primary,
                            modifier = Modifier.minimumInteractiveComponentSize(),
                        )
                        actions()
                    },
                )
            },
            value = selectedOption.orEmpty(),
            onValueChange = {},
            colors = tasksAppTextFieldButtonColors(),
            modifier =
                Modifier
                    .nullableTestTag(tag = textFieldTestTag)
                    .fillMaxWidth(),
        )
        supportingText
            ?.let { content ->
                Spacer(modifier = Modifier.height(height = 6.dp))
                TasksAppHorizontalDivider(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier =
                        Modifier
                            .defaultMinSize(minHeight = 48.dp)
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                    content = {
                        Text(
                            text = content,
                            style = TasksAppTheme.typography.bodySmall,
                            color = TasksAppTheme.colorScheme.text.secondary,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            }
            ?: Spacer(modifier = Modifier.height(height = cardStyle?.let { 6.dp } ?: 0.dp))
    }
}

@Preview
@Composable
private fun TasksAppTextSelectionButton_preview() {
    TasksAppTheme {
        TasksAppTextSelectionButton(
            label = "Folder",
            selectedOption = "No Folder",
            onClick = {},
            cardStyle = CardStyle.Full,
        )
    }
}
