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
package com.app.tasks.core.dropdown

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.core.button.TasksAppTextSelectionButton
import com.app.tasks.core.dialog.TasksAppSelectionDialog
import com.app.tasks.core.dialog.TasksAppSelectionRow
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.field.TooltipData
import com.app.tasks.core.utils.asText
import com.app.tasks.ui.theme.TasksAppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * A custom composable representing a multi-select button.
 *
 * This composable displays an [OutlinedTextField] with a dropdown icon as a trailing icon.
 * When the field is clicked, a dropdown menu appears with a list of options to select from.
 *
 * @param label The descriptive text label for the [OutlinedTextField].
 * @param options A list of strings representing the available options in the dialog.
 * @param selectedOption The currently selected option that is displayed in the [OutlinedTextField]
 * (or `null` if no option is selected).
 * @param onOptionSelected A lambda that is invoked when an option
 * is selected from the dropdown menu.
 * @param isEnabled Whether or not the button is enabled.
 * @param cardStyle Indicates the type of card style to be applied.
 * @param modifier A [Modifier] that you can use to apply custom modifications to the composable.
 * @param supportingText A optional supporting text that will appear below the text field.
 * @param tooltip A nullable [TooltipData], representing the tooltip icon.
 * @param insets Inner padding to be applied withing the card.
 * @param textFieldTestTag The optional test tag associated with the inner text field.
 * @param actionsPadding Padding to be applied to the [actions] block.
 * @param actions A lambda containing the set of actions (usually icons or similar) to display
 * in the app bar's trailing side. This lambda extends [RowScope], allowing flexibility in
 * defining the layout of the actions.
 */
@Composable
fun TasksAppMultiSelectButton(
    label: String,
    options: ImmutableList<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    cardStyle: CardStyle?,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    supportingText: String? = null,
    tooltip: TooltipData? = null,
    insets: PaddingValues = PaddingValues(),
    textFieldTestTag: String? = null,
    actionsPadding: PaddingValues = PaddingValues(end = 4.dp),
    actions: @Composable RowScope.() -> Unit = {},
) {
    var shouldShowDialog by rememberSaveable { mutableStateOf(false) }

    TasksAppTextSelectionButton(
        label = label,
        selectedOption = selectedOption,
        onClick = {
            shouldShowDialog = true
        },
        cardStyle = cardStyle,
        enabled = isEnabled,
        supportingText = supportingText,
        tooltip = tooltip,
        insets = insets,
        textFieldTestTag = textFieldTestTag,
        actionsPadding = actionsPadding,
        actions = actions,
        semanticRole = Role.DropdownList,
        modifier = modifier,
    )

    if (shouldShowDialog) {
        TasksAppSelectionDialog(
            title = label,
            onDismissRequest = { shouldShowDialog = false },
        ) {
            options.forEach { optionString ->
                TasksAppSelectionRow(
                    text = optionString.asText(),
                    isSelected = optionString == selectedOption,
                    onClick = {
                        shouldShowDialog = false
                        onOptionSelected(optionString)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun TasksAppMultiSelectButton_preview() {
    TasksAppTheme {
        TasksAppMultiSelectButton(
            label = "Label",
            options = persistentListOf("a", "b"),
            selectedOption = "",
            onOptionSelected = {},
            cardStyle = CardStyle.Full,
        )
    }
}
