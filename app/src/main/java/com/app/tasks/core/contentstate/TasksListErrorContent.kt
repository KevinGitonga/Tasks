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
package com.app.tasks.core.contentstate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A TasksApp-themed, re-usable error state.
 *
 * Note that when [onTryAgainClick] is absent, there will be no "Try again" button displayed.
 */
@Composable
fun TasksListErrorContent(
    message: String,
    modifier: Modifier = Modifier,
    onTryAgainClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = message,
            color = TasksAppTheme.colorScheme.text.primary,
            style = TasksAppTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
        )
        onTryAgainClick?.let {
            Spacer(modifier = Modifier.height(16.dp))
            TasksAppTextButton(
                label = stringResource(id = R.string.try_again),
                onClick = it,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}
