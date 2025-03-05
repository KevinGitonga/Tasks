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
package com.app.tasks.modules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.app.tasks.R
import com.app.tasks.core.appbar.TasksAppMediumTopAppBar
import com.app.tasks.core.fab.TasksAppFloatingActionButton
import com.app.tasks.core.scaffold.TasksAppScaffold
import com.app.tasks.core.utils.rememberVectorPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TasksAppScaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TasksAppMediumTopAppBar(
                title = "Tasks",
                scrollBehavior = scrollBehavior,
                actions = {
                    TasksFilterActionItem(
                        contentDescription = stringResource(R.string.filter),
                        onClick = {},
                    )
                },
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = true,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                TasksAppFloatingActionButton(
                    onClick = {},
                    painter = rememberVectorPainter(id = R.drawable.ic_plus_large),
                    contentDescription = stringResource(id = R.string.create_task),
                    modifier = Modifier.testTag(tag = "AddTaskButton"),
                )
            }
        },
        content = {
        },
    )
}
