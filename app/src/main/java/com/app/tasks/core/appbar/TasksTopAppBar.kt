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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.tasks.R
import com.app.tasks.core.appbar.color.soccerScoresTopAppBarColors
import com.app.tasks.core.button.TasksAppStandardIconButton
import com.app.tasks.core.extensions.bottomDivider
import com.app.tasks.core.extensions.mirrorIfRtl
import com.app.tasks.core.extensions.scrolledContainerBottomDivider
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * Represents a TasksApp styled [TopAppBar] that assumes the following components:
 *
 * - a single navigation control in the upper-left defined by [navigationIcon],
 *   [navigationIconContentDescription], and [onNavigationIconClick].
 * - a [title] in the middle.
 * - a [actions] lambda containing the set of actions (usually icons or similar) to display
 *  in the app bar's trailing side. This lambda extends [RowScope], allowing flexibility in
 *  defining the layout of the actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: Painter,
    navigationIconContentDescription: String,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets =
        TopAppBarDefaults.windowInsets
            .union(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)),
    dividerStyle: TopAppBarDividerStyle = TopAppBarDividerStyle.ON_SCROLL,
    actions: @Composable RowScope.() -> Unit = { },
) {
    TasksTopAppBar(
        title = title,
        scrollBehavior = scrollBehavior,
        navigationIcon =
            NavigationIcon(
                navigationIcon = navigationIcon,
                navigationIconContentDescription = navigationIconContentDescription,
                onNavigationIconClick = onNavigationIconClick,
            ),
        modifier = modifier,
        windowInsets = windowInsets,
        dividerStyle = dividerStyle,
        actions = actions,
    )
}

/**
 * Represents a TasksApp styled [TopAppBar] that assumes the following components:
 *
 * - an optional single navigation control in the upper-left defined by [navigationIcon].
 * - a [title] in the middle.
 * - a [actions] lambda containing the set of actions (usually icons or similar) to display
 *  in the app bar's trailing side. This lambda extends [RowScope], allowing flexibility in
 *  defining the layout of the actions.
 * - if the title text causes an overflow in the standard material [TopAppBar] a [MediumTopAppBar]
 *   will be used instead, droping the title text to a second row beneath the [navigationIcon] and
 *   [actions].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongMethod")
@Composable
fun TasksTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: NavigationIcon?,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets =
        TopAppBarDefaults.windowInsets
            .union(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)),
    dividerStyle: TopAppBarDividerStyle = TopAppBarDividerStyle.ON_SCROLL,
    actions: @Composable RowScope.() -> Unit = {},
    minimumHeight: Dp = 48.dp,
) {
    var titleTextHasOverflow by remember {
        mutableStateOf(false)
    }

    val navigationIconContent: @Composable () -> Unit =
        remember(navigationIcon) {
            {
                navigationIcon?.let {
                    TasksAppStandardIconButton(
                        painter = it.navigationIcon,
                        contentDescription = it.navigationIconContentDescription,
                        onClick = it.onNavigationIconClick,
                        modifier =
                            Modifier
                                .testTag(tag = "CloseButton")
                                .mirrorIfRtl(),
                    )
                }
            }
        }
    val customModifier =
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
            )

    if (titleTextHasOverflow) {
        MediumTopAppBar(
            windowInsets = windowInsets,
            colors = soccerScoresTopAppBarColors(),
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIconContent,
            collapsedHeight = minimumHeight,
            expandedHeight = 96.dp,
            title = {
                // The height of the component is controlled and will only allow for 1 extra row,
                // making adding any arguments for softWrap and minLines superfluous.
                Text(
                    text = title,
                    style = TasksAppTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(tag = "PageTitleLabel"),
                )
            },
            modifier = customModifier,
            actions = actions,
        )
    } else {
        TopAppBar(
            windowInsets = windowInsets,
            colors = soccerScoresTopAppBarColors(),
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIconContent,
            expandedHeight = minimumHeight,
            title = {
                Text(
                    text = title,
                    style = TasksAppTheme.typography.titleLarge,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(tag = "PageTitleLabel"),
                    onTextLayout = {
                        titleTextHasOverflow = it.hasVisualOverflow
                    },
                )
            },
            modifier = customModifier,
            actions = actions,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TasksTopAppBar_preview() {
    TasksAppTheme {
        TasksTopAppBar(
            title = "Title",
            scrollBehavior =
                TopAppBarDefaults
                    .exitUntilCollapsedScrollBehavior(
                        rememberTopAppBarState(),
                    ),
            navigationIcon =
                NavigationIcon(
                    navigationIcon = rememberVectorPainter(id = R.drawable.ic_back),
                    navigationIconContentDescription = stringResource(id = R.string.back),
                    onNavigationIconClick = { },
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TasksTopAppBarOverflow_preview() {
    TasksAppTheme {
        TasksTopAppBar(
            title = "Title that is too long for the top line",
            scrollBehavior =
                TopAppBarDefaults
                    .exitUntilCollapsedScrollBehavior(
                        rememberTopAppBarState(),
                    ),
            navigationIcon =
                NavigationIcon(
                    navigationIcon = rememberVectorPainter(id = R.drawable.ic_back),
                    navigationIconContentDescription = stringResource(id = R.string.back),
                    onNavigationIconClick = { },
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TasksTopAppBarOverflowCutoff_preview() {
    TasksAppTheme {
        TasksTopAppBar(
            title = "Title that is too long for the top line and the bottom line",
            scrollBehavior =
                TopAppBarDefaults
                    .exitUntilCollapsedScrollBehavior(
                        rememberTopAppBarState(),
                    ),
            navigationIcon =
                NavigationIcon(
                    navigationIcon = rememberVectorPainter(id = R.drawable.ic_back),
                    navigationIconContentDescription = stringResource(id = R.string.back),
                    onNavigationIconClick = { },
                ),
        )
    }
}

/**
 * Represents all data required to display a [navigationIcon].
 *
 * @property navigationIcon The [Painter] displayed as part of the icon.
 * @property navigationIconContentDescription The content description associated with the icon.
 * @property onNavigationIconClick The click action that is invoked when the icon is tapped.
 */
data class NavigationIcon(
    val navigationIcon: Painter,
    val navigationIconContentDescription: String,
    val onNavigationIconClick: () -> Unit,
)
