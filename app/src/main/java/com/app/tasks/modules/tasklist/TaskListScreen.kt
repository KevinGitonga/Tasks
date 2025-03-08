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
package com.app.tasks.modules.tasklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.tasks.R
import com.app.tasks.core.appbar.TasksAppMediumTopAppBar
import com.app.tasks.core.base.EventsEffect
import com.app.tasks.core.contentstate.TaskListNoItems
import com.app.tasks.core.contentstate.TasksListErrorContent
import com.app.tasks.core.contentstate.TasksListLoadingContent
import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.dialog.TasksAppSelectionDialog
import com.app.tasks.core.dialog.TasksAppSelectionRow
import com.app.tasks.core.fab.TasksAppFloatingActionButton
import com.app.tasks.core.scaffold.TasksAppScaffold
import com.app.tasks.core.utils.asText
import com.app.tasks.core.utils.rememberVectorPainter
import com.app.tasks.modules.settings.components.SettingsActionItem
import com.app.tasks.modules.tasklist.components.TaskListHeaderItem
import com.app.tasks.modules.tasklist.components.TaskListItem
import com.app.tasks.modules.tasklist.components.TaskListProgressItem
import com.app.tasks.modules.tasklist.components.TasksFilterActionItem
import com.app.tasks.modules.tasklist.models.TasksSectionData
import com.app.tasks.navigation.Destinations
import com.app.tasks.navigation.NavigationConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskListViewModel = hiltViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    EventsEffect(viewModel = viewModel) { event ->
        when (event) {
            TaskListEvents.NavigateToTaskAddScreen -> {
                navController.navigate(
                    "${Destinations.TaskDetails.route}/${NavigationConstants.Key.CREATE_TASK_DUMMY_ID}/${NavigationConstants.Key.CREATE_TASK_NAV}",
                )
            }
            is TaskListEvents.NavigateToTaskEditScreen -> {
                navController.navigate("${Destinations.TaskDetails.route}/${event.taskId}/${NavigationConstants.Key.EDIT_TASK_NAV}")
            }

            TaskListEvents.NavigateToSettingsScreen -> {
                navController.navigate(Destinations.Settings.route)
            }

            is TaskListEvents.NavigateToTaskDeleteScreen -> {
                navController.navigate("${Destinations.TaskDetails.route}/${event.taskId}/${NavigationConstants.Key.DELETE_TASK_NAV}")
            }
        }
    }

    if (state.showSortDialog) {
        TasksAppSelectionDialog(
            title = stringResource(R.string.sort_tasks),
            onDismissRequest = {
                viewModel.trySendAction(TaskListAction.DismissSortDialog)
            },
            selectionItems = {
                viewModel.sortParams.forEach {
                    TasksAppSelectionRow(
                        text = it.name.asText(),
                        onClick = {
                            viewModel.trySendAction(TaskListAction.TaskSortChangeAction(it))
                        },
                        isSelected = it.sortName == state.sortBy.sortName,
                    )
                }
            },
        )
    }

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
                        onClick = {
                            viewModel.trySendAction(TaskListAction.ShowFilterDialogAction)
                        },
                    )

                    SettingsActionItem(
                        contentDescription = stringResource(R.string.settings),
                        onClick = {
                            viewModel.trySendAction(TaskListAction.ShowSettingsScreenAction)
                        },
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
                    onClick = {
                        viewModel.trySendAction(TaskListAction.AddTaskClick)
                    },
                    painter = rememberVectorPainter(id = R.drawable.ic_plus_large),
                    contentDescription = stringResource(id = R.string.create_task),
                    modifier = Modifier.testTag(tag = "AddTaskButton"),
                )
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                when (state.viewState) {
                    is TaskListState.ViewState.Content -> {
                        val listState = rememberLazyListState()
                        val dataList = remember { (state.viewState as TaskListState.ViewState.Content).taskListData }

                        val isExpandedMap =
                            remember {
                                List(
                                    (state.viewState as TaskListState.ViewState.Content).taskListData.size,
                                ) { index: Int -> index to dataList[index].isExpanded }
                                    .toMutableStateMap()
                            }

                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize(),
                            userScrollEnabled = true,
                            content = {
                                if (dataList.isNotEmpty()) {
                                    if (dataList.first().completedTasksCount > 0) {
                                        val progressRate =
                                            (
                                                dataList.first().completedTasksCount.toDouble() /
                                                    dataList.first().allTasksCount
                                            ) * 100

                                        item {
                                            TaskListProgressItem(
                                                progress = progressRate,
                                            )
                                        }
                                    }
                                }

                                dataList
                                    .onEachIndexed { index, sectionData ->
                                        section(
                                            tasksData = sectionData,
                                            isExpanded =
                                                if (isExpandedMap[index] == true) {
                                                    true
                                                } else if (sectionData.taskHeaderItemModel.isExpanded) {
                                                    true
                                                } else {
                                                    false
                                                },
                                            onHeaderClick = {
                                                if (isExpandedMap[index] == true) {
                                                    isExpandedMap[index] = false
                                                    sectionData.taskHeaderItemModel.isExpanded = false
                                                } else {
                                                    isExpandedMap[index] = true
                                                }
                                            },
                                            onTaskItemClick = {
                                                viewModel.trySendAction(TaskListAction.TaskItemClickAction(it))
                                            },
                                            currentPosition = index,
                                            dataSize = dataList.lastIndex,
                                        )
                                    }
                            },
                        )
                    }

                    is TaskListState.ViewState.Error -> {
                        TasksListErrorContent(
                            message = (state.viewState as TaskListState.ViewState.Error).message,
                            onTryAgainClick = {
                            },
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    TaskListState.ViewState.Loading -> {
                        TasksListLoadingContent(
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    TaskListState.ViewState.NoItems -> {
                        TaskListNoItems(
                            message = stringResource(R.string.no_tasks_available),
                            modifier = Modifier.fillMaxSize(),
                            vectorRes = (R.drawable.ic_empty_state),
                        )
                    }
                }
            }
        },
    )
}

fun LazyListScope.section(
    tasksData: TasksSectionData,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit,
    onTaskItemClick: (TaskEntity) -> Unit,
    currentPosition: Int,
    dataSize: Int,
) {
    item {
        TaskListHeaderItem(
            taskHeaderItemModel = tasksData.taskHeaderItemModel,
            onClick = onHeaderClick,
            isExpanded = isExpanded,
        )
    }

    if (isExpanded) {
        items(tasksData.tasks, key = { task -> task.taskId }) { task ->
            TaskListItem(
                taskEntity = task,
                onClick = {
                    onTaskItemClick(it)
                },
            )
        }
    }

    if (currentPosition == dataSize) {
        item {
            Spacer(modifier = Modifier.height(height = 70.dp))
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}
