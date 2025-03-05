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
package com.app.tasks.core.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Direct passthrough to [Scaffold] but contains a few specific override values. Everything is
 * still overridable if necessary.
 *
 * The [utilityBar] is a nonstandard [Composable] that is placed below the [topBar] and does not
 * scroll.
 * The [overlay] is a nonstandard [Composable] that is placed over top the `utilityBar` and
 * `content`.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TasksAppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    utilityBar: @Composable () -> Unit = { },
    overlay: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    snackbarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = { },
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    pullToRefreshState: TasksAppPullToRefreshState = rememberTasksAppPullToRefreshState(),
    containerColor: Color = TasksAppTheme.colorScheme.background.primary,
    contentColor: Color = TasksAppTheme.colorScheme.text.primary,
    contentWindowInsets: WindowInsets =
        ScaffoldDefaults
            .contentWindowInsets
            .union(WindowInsets.displayCutout)
            .only(WindowInsetsSides.Horizontal),
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier =
            Modifier
                .semantics { testTagsAsResourceId = true }
                .then(modifier),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = WindowInsets(0.dp),
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
                utilityBar()
                val internalPullToRefreshState = rememberPullToRefreshState()
                Box(
                    modifier =
                        Modifier
                            .windowInsetsPadding(insets = contentWindowInsets)
                            .pullToRefresh(
                                state = internalPullToRefreshState,
                                isRefreshing = pullToRefreshState.isRefreshing,
                                onRefresh = pullToRefreshState.onRefresh,
                                enabled = pullToRefreshState.isEnabled,
                            ),
                ) {
                    content()

                    PullToRefreshDefaults.Indicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = pullToRefreshState.isRefreshing,
                        state = internalPullToRefreshState,
                        containerColor = TasksAppTheme.colorScheme.background.secondary,
                        color = TasksAppTheme.colorScheme.icon.secondary,
                    )
                }
            }
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                overlay()
            }
        },
    )
}

/**
 * The state of the pull-to-refresh.
 */
data class TasksAppPullToRefreshState(
    val isEnabled: Boolean,
    val isRefreshing: Boolean,
    val onRefresh: () -> Unit,
)

/**
 * Create and remember the default [TasksAppPullToRefreshState].
 */
@Composable
fun rememberTasksAppPullToRefreshState(
    isEnabled: Boolean = false,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = { },
): TasksAppPullToRefreshState =
    remember(isEnabled, isRefreshing, onRefresh) {
        TasksAppPullToRefreshState(
            isEnabled = isEnabled,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        )
    }
