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
package com.app.tasks.modules.taskdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.app.tasks.core.appbar.NavigationIcon
import com.app.tasks.core.appbar.TasksTopAppBar
import com.app.tasks.core.base.EventsEffect
import com.app.tasks.core.button.TasksAppTextButton
import com.app.tasks.core.button.TasksAppTextSelectionButton
import com.app.tasks.core.contentstate.TasksListLoadingContent
import com.app.tasks.core.datepicker.TasksDatePicker
import com.app.tasks.core.dialog.TasksAppSelectionDialog
import com.app.tasks.core.dialog.TasksAppSelectionRow
import com.app.tasks.core.extensions.CardStyle
import com.app.tasks.core.extensions.orNow
import com.app.tasks.core.extensions.standardHorizontalMargin
import com.app.tasks.core.field.TasksAppTextField
import com.app.tasks.core.header.TasksAppListHeaderText
import com.app.tasks.core.scaffold.TasksAppScaffold
import com.app.tasks.core.utils.asText
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.core.utils.toFormattedPattern
import timber.log.Timber
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // This tracks the date component (year, month, and day) and ignores lower level
    // components.
    var date: ZonedDateTime? by remember { mutableStateOf(state.taskDueDate) }
    // This tracks just the time component (hours and minutes) and ignores the higher level
    // components. 0 representing midnight and counting up from there.
    var timeMillis: Long by remember {
        mutableLongStateOf(
            state.taskDueDate.let {
                it.hour.hours.inWholeMilliseconds + it.minute.minutes.inWholeMilliseconds
            },
        )
    }
    val derivedDateTimeMillis: ZonedDateTime? by remember {
        derivedStateOf { date?.plus(timeMillis, ChronoUnit.MILLIS) }
    }

    EventsEffect(viewModel = viewModel) { event ->
        when (event) {
            AddTaskEvent.ShowSuccessToast -> {
                Toast
                    .makeText(context, context.getString(R.string.task_saved_successfully), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    if (state.showPriorityDialog) {
        TasksAppSelectionDialog(
            title = stringResource(R.string.select_priority),
            onDismissRequest = {
            },
            selectionItems = {
                viewModel.taskPriorities.forEach {
                    TasksAppSelectionRow(
                        text = it.name.asText(),
                        onClick = {
                            viewModel.trySendAction(AddTaskAction.TaskPrioritySelectionChange(it))
                        },
                        isSelected = it.name == state.taskPriority.name,
                    )
                }
            },
        )
    }

    if (state.showDatePickerDialog) {
        val datePickerState =
            rememberDatePickerState(
                initialSelectedDateMillis = state.taskDueDate.orNow().toInstant().toEpochMilli(),
            )

        TasksDatePicker(
            currentZonedDateTime = state.taskDueDate,
            onDateSelect = {
                Timber.e("date data $it")
                date = it
                viewModel.trySendAction(AddTaskAction.DueDateSelectionChange(requireNotNull(derivedDateTimeMillis)))
            },
            onDismiss = {},
            datePickerState = datePickerState,
        )
    }

    TasksAppScaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TasksTopAppBar(
                title = stringResource(R.string.tasks_details),
                scrollBehavior = scrollBehavior,
                navigationIcon =
                    NavigationIcon(
                        navigationIcon = rememberVectorPainter(id = R.drawable.ic_close),
                        navigationIconContentDescription = stringResource(id = R.string.close),
                        onNavigationIconClick = {
                            navController.popBackStack()
                        },
                    ),
                actions = {
                    TasksAppTextButton(
                        label = stringResource(id = R.string.save),
                        onClick = {
                            viewModel.trySendAction(AddTaskAction.SaveTaskClick)
                        },
                        modifier = Modifier.testTag("SaveButton"),
                    )
                },
            )
        },
        content = {
            when (state.viewState) {
                is AddTaskState.ViewState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Spacer(modifier = Modifier.height(height = 12.dp))
                        }

                        item {
                            TasksAppListHeaderText(
                                label = stringResource(id = R.string.task_details),
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .standardHorizontalMargin()
                                        .padding(horizontal = 16.dp),
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 12.dp))
                        }

                        item {
                            TasksAppTextField(
                                label = stringResource(id = R.string.task_title),
                                cardStyle = CardStyle.Full,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .standardHorizontalMargin(),
                                onValueChange = {
                                    viewModel.trySendAction(AddTaskAction.TitleTextChange(it))
                                },
                                value = state.taskTitle,
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 12.dp))
                        }

                        item {
                            TasksAppTextField(
                                label = stringResource(id = R.string.task_description),
                                cardStyle = CardStyle.Full,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .standardHorizontalMargin(),
                                onValueChange = {
                                    viewModel.trySendAction(AddTaskAction.DescriptionTextChange(it))
                                },
                                value = state.taskDescription,
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 12.dp))
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 8.dp))
                            TasksAppTextSelectionButton(
                                label = stringResource(id = R.string.priority),
                                selectedOption = state.taskPriority.name,
                                onClick = {
                                    viewModel.trySendAction(AddTaskAction.PrioritySelectionDialog)
                                },
                                cardStyle =
                                    CardStyle.Top(dividerPadding = 0.dp),
                                modifier =
                                    Modifier
                                        .testTag(tag = "PriorityPicker")
                                        .fillMaxWidth()
                                        .standardHorizontalMargin(),
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 12.dp))
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 8.dp))
                            TasksAppTextSelectionButton(
                                label = stringResource(id = R.string.due_date),
                                selectedOption = state.taskDueDate.toFormattedPattern("M/d/yyyy"),
                                onClick = {
                                    viewModel.trySendAction(AddTaskAction.DatePickerDialogAction)
                                },
                                cardStyle =
                                    CardStyle.Top(dividerPadding = 0.dp),
                                modifier =
                                    Modifier
                                        .testTag(tag = "DatePicker")
                                        .fillMaxWidth()
                                        .standardHorizontalMargin(),
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(height = 16.dp))
                            Spacer(modifier = Modifier.navigationBarsPadding())
                        }
                    }
                }

                is AddTaskState.ViewState.Error -> {}
                AddTaskState.ViewState.Loading -> {
                    TasksListLoadingContent(
                        text = stringResource(R.string.saving_task_please_wait),
                    )
                }
            }
        },
    )
}
