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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.tasks.core.extensions.standardHorizontalMargin
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

@Composable
fun TaskListNoItems(
    modifier: Modifier,
    @DrawableRes vectorRes: Int? = null,
    headerText: String? = null,
    message: String,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1F))

        vectorRes?.let {
            Image(
                painter = rememberVectorPainter(id = it),
                contentDescription = null,
                modifier =
                    Modifier
                        .standardHorizontalMargin()
                        .size(300.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
        headerText?.let {
            Text(
                textAlign = TextAlign.Center,
                text = it,
                style = TasksAppTheme.typography.titleMedium,
                color = TasksAppTheme.colorScheme.text.primary,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .standardHorizontalMargin(),
            )
            Spacer(Modifier.height(12.dp))
        }
        Text(
            textAlign = TextAlign.Center,
            text = message,
            style = TasksAppTheme.typography.bodyMedium,
            color = TasksAppTheme.colorScheme.text.primary,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .standardHorizontalMargin(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.weight(1F))
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}
