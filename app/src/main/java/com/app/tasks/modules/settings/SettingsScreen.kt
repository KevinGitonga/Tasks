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
package com.app.tasks.modules.settings

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.tasks.R
import com.app.tasks.core.AppTheme
import com.app.tasks.core.appbar.NavigationIcon
import com.app.tasks.core.appbar.TasksTopAppBar
import com.app.tasks.core.base.EventsEffect
import com.app.tasks.core.dropdown.TasksAppMultiSelectButton
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.extensions.standardHorizontalMargin
import com.app.tasks.core.scaffold.TasksAppScaffold
import com.app.tasks.core.utils.displayLabel
import com.app.tasks.core.utils.rememberVectorPainter
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    EventsEffect(viewModel = viewModel) { event ->
        when (event) {
            SettingsEvent.NavigateBack -> navController.popBackStack()
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TasksAppScaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TasksTopAppBar(
                title = stringResource(R.string.settings),
                scrollBehavior = scrollBehavior,
                navigationIcon =
                    NavigationIcon(
                        navigationIcon = rememberVectorPainter(id = R.drawable.ic_back),
                        navigationIconContentDescription = stringResource(id = R.string.close),
                        onNavigationIconClick = {
                            viewModel.trySendAction(SettingsAction.BackClick)
                        },
                    ),
            )
        },
        content = {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(height = 12.dp))
                ThemeSelectionRow(
                    currentSelection = state.theme,
                    onThemeSelection =
                        remember(viewModel) {
                            { viewModel.trySendAction(SettingsAction.ThemeChange(it)) }
                        },
                    modifier =
                        Modifier
                            .testTag("ThemeChooser")
                            .standardHorizontalMargin()
                            .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        },
    )
}

@Composable
private fun ThemeSelectionRow(
    currentSelection: AppTheme,
    onThemeSelection: (AppTheme) -> Unit,
    modifier: Modifier = Modifier,
    resources: Resources = LocalContext.current.resources,
) {
    TasksAppMultiSelectButton(
        label = stringResource(id = R.string.theme),
        options = AppTheme.entries.map { it.displayLabel() }.toImmutableList(),
        selectedOption = currentSelection.displayLabel(),
        onOptionSelected = { selectedTheme ->
            onThemeSelection(
                AppTheme.entries.first { selectedTheme == it.displayLabel.toString(resources) },
            )
        },
        supportingText = stringResource(id = R.string.theme_description),
        cardStyle = CardStyle.Full,
        modifier = modifier,
    )
}
