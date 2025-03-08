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
package com.app.tasks.core.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.union
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.appbar.color.soccerScoresTopAppBarColors
import com.app.tasks.core.button.TasksAppStandardIconButton
import com.app.tasks.core.extensions.bottomDivider
import com.app.tasks.core.extensions.scrolledContainerBottomDivider
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A custom TasksApp-themed medium top app bar with support for actions.
 *
 * This app bar wraps around Material 3's [MediumTopAppBar] and customizes its appearance
 * and behavior according to the app theme.
 * It provides a title and an optional set of actions on the trailing side.
 * These actions are arranged within a custom action row tailored to the app's design requirements.
 *
 * @param title The text to be displayed as the title of the app bar.
 * @param scrollBehavior Defines the scrolling behavior of the app bar. It controls how the app bar
 * behaves in conjunction with scrolling content.
 * @param windowInsets The insets to be applied to this composable.
 * @param dividerStyle Determines how the bottom divider should be displayed.
 * @param actions A lambda containing the set of actions (usually icons or similar) to display
 * in the app bar's trailing side. This lambda extends [RowScope], allowing flexibility in
 * defining the layout of the actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksAppMediumTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets =
        TopAppBarDefaults.windowInsets
            .union(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)),
    dividerStyle: TopAppBarDividerStyle = TopAppBarDividerStyle.ON_SCROLL,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        windowInsets = windowInsets,
        colors = soccerScoresTopAppBarColors(),
        scrollBehavior = scrollBehavior,
        expandedHeight = 56.dp,
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TasksAppTheme.typography.headlineMedium,
                modifier = Modifier.testTag(tag = "PageTitleLabel"),
            )
        },
        modifier =
            modifier
                .testTag(tag = "HeaderBarComponent")
                .scrolledContainerBottomDivider(
                    topAppBarScrollBehavior = scrollBehavior,
                    enabled =
                        when (dividerStyle) {
                            TopAppBarDividerStyle.NONE -> false
                            TopAppBarDividerStyle.STATIC -> false
                            TopAppBarDividerStyle.ON_SCROLL -> true
                        },
                ).bottomDivider(
                    enabled =
                        when (dividerStyle) {
                            TopAppBarDividerStyle.NONE -> false
                            TopAppBarDividerStyle.STATIC -> true
                            TopAppBarDividerStyle.ON_SCROLL -> false
                        },
                ),
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TasksAppMediumTopAppBar_preview() {
    TasksAppTheme {
        TasksAppMediumTopAppBar(
            title = stringResource(R.string.app_name),
            scrollBehavior =
                TopAppBarDefaults
                    .exitUntilCollapsedScrollBehavior(
                        rememberTopAppBarState(),
                    ),
            actions = {
                TasksAppStandardIconButton(
                    vectorIconRes = R.drawable.ic_back,
                    contentDescription = "",
                    onClick = {},
                )
                TasksAppStandardIconButton(
                    vectorIconRes = R.drawable.ic_back,
                    contentDescription = "",
                    onClick = { },
                )
            },
        )
    }
}
