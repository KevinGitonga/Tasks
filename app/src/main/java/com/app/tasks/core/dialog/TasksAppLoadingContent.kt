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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A SoccerScores-themed, re-usable loading state.
 */
@Composable
fun TasksAppLoadingContent(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        text?.let {
            Text(
                text = it,
                style = TasksAppTheme.typography.titleMedium,
                // setting color explicitly here as we can't assume what the surface will be.
                color = TasksAppTheme.colorScheme.text.primary,
                modifier = Modifier.testTag(tag = "AlertTitleText"),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        androidx.compose.material3.CircularProgressIndicator(
            modifier =
                Modifier
                    .size(48.dp)
                    .testTag(tag = "AlertProgressIndicator"),
        )
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}
