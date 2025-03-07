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
package com.app.tasks.core.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a SoccerScores-styled loading dialog that shows text and a circular progress indicator.
 *
 * This implementation uses a `Popup` because the standard `Dialog` did not work with the intended
 * designs. When using a `Dialog`, the status bar was appearing dark and the content was going below
 * the navigation bar. To ensure the loading overlay fully covers the entire screen—including the
 * status bar area, `Popup` was used with `clippingEnabled = false`. This allows it to extend
 * beyond the default bounds, ensuring a full-screen design.
 *
 * We retained the Dialog nomenclature to minimize refactor disruption, but we plan to transition
 * to Modal-based terminology in the future. (https://bitwarden.atlassian.net/browse/PM-17356)
 *
 * @param text The text to display in the dialog.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TasksAppLoadingDialog(
    text: String,
) {
    Popup(
        properties =
            PopupProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                clippingEnabled = false,
            ),
    ) {
        TasksAppLoadingContent(
            text = text,
            modifier =
                Modifier
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "AlertPopup"
                    }
                    .fillMaxSize()
                    .background(
                        color = TasksAppTheme.colorScheme.background.secondary.copy(alpha = 0.90f),
                    ),
        )
    }
}

@Preview
@Composable
private fun SoccerScoresLoadingDialog_preview() {
    TasksAppTheme {
        TasksAppLoadingDialog(text = "Loading...")
    }
}
