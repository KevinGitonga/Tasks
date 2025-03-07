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

import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.app.tasks.core.base.BaseViewModel
import com.app.tasks.core.constants.TaskStatuses
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepository
import com.app.tasks.modules.tasklist.models.TaskHeaderItemModel
import com.app.tasks.modules.tasklist.models.TasksSectionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel
    @Inject
    constructor(
        private val localDataRepository: LocalDataRepository,
    ) : BaseViewModel<TaskListState, TaskListEvents, TaskListAction>(
            initialState =
                TaskListState(
                    viewState = TaskListState.ViewState.Loading,
                    sortBy = SortBy.DueDate,
                    filterBy = TaskStatuses.Pending,
                ),
        ) {
        val sortParams = SortBy.entries.toList()

        init {
            loadTasks()
        }

        private fun loadTasks() {
            viewModelScope.launch {
                delay(1000)
                localDataRepository.getTasks().collectLatest { it1 ->
                    mutableStateFlow.update { it3 ->
                        it3.copy(
                            viewState =
                                if (it1.isEmpty()) {
                                    TaskListState.ViewState.NoItems
                                } else {
                                    Timber.e("TASKS DATA" + it1.count())
                                    val tasksData =
                                        it1.groupBy { it.taskStatus }

                                    TaskListState.ViewState.Content(
                                        taskListData =
                                            tasksData.map { entry ->
                                                val sortedTasks =
                                                    when (state.sortBy) {
                                                        SortBy.DueDate -> {
                                                            entry.value.sortedByDescending { it.dueDate.toLocalDate() }
                                                        }
                                                        SortBy.Alphabet -> {
                                                            entry.value.sortedBy { it.taskId }
                                                        }
                                                        else -> {
                                                            entry.value.sortedByDescending { it.priority }
                                                        }
                                                    }

                                                TasksSectionData(
                                                    taskHeaderItemModel =
                                                        TaskHeaderItemModel(
                                                            sectionName = entry.key,
                                                            totalItemsCount = entry.value.count(),
                                                            isExpanded = entry.key == state.filterBy.name,
                                                        ),
                                                    tasks = sortedTasks,
                                                    isExpanded = entry.key == state.filterBy.name,
                                                    onHeaderClicked = {},
                                                )
                                            },
                                    )
                                },
                        )
                    }
                }
            }
        }

        override fun handleAction(action: TaskListAction) {
            when (action) {
                TaskListAction.AddTaskClick -> {
                    sendEvent(TaskListEvents.NavigateToTaskAddEditScreen)
                }

                TaskListAction.ShowFilterDialogAction -> {
                    mutableStateFlow.update {
                        it.copy(
                            showSortDialog = true,
                        )
                    }
                }

                is TaskListAction.TaskSortChangeAction -> {
                    mutableStateFlow.update {
                        it.copy(
                            showSortDialog = false,
                            sortBy = action.newFilter,
                            viewState = TaskListState.ViewState.Loading,
                        )
                    }

                    loadTasks()
                }
            }
        }
    }

/**
 * Models state for the [TaskListViewModel].
 */
data class TaskListState(
    val viewState: ViewState,
    val sortBy: SortBy,
    val filterBy: TaskStatuses,
    val showSortDialog: Boolean = false,
) {
    /**
     * Represents the specific view states for the [TaskListScreen].
     */
    @Parcelize
    sealed class ViewState : Parcelable {
        /**
         * Represents an error state for the [TaskListScreen].
         *
         * @property message Error message to display.
         */
        data class Error(
            val message: String,
        ) : ViewState()

        /**
         * Loading state for the [TaskListScreen],
         * signifying that the content is being processed.
         */
        data object Loading : ViewState()

        /**
         * Represents a state where the [TaskListScreen] has no items to display.
         */
        @Parcelize
        data object NoItems : ViewState()

        /**
         * Content state for the [TaskListScreen] showing the actual content or items.
         */
        data class Content(
            val taskListData: List<TasksSectionData>,
        ) : ViewState()
    }
}

/**
 * Models events for the [TaskListScreen].
 */
sealed class TaskListEvents {
    /**
     * Navigate to [TaskListScreen].
     */
    data object NavigateToTaskAddEditScreen : TaskListEvents()
}

/**
 * Models actions for the [TaskListScreen].
 */
sealed class TaskListAction {
    /**
     * Add task Click.
     */
    data object AddTaskClick : TaskListAction()

    /**
     * Show filter Dialog.
     */
    data object ShowFilterDialogAction : TaskListAction()

    /**
     * Tasks filter param change.
     */
    data class TaskSortChangeAction(val newFilter: SortBy) : TaskListAction()
}

enum class SortBy(
    val sortName: String,
) {
    Priority("priority"),
    DueDate(sortName = "due_date"),
    Alphabet(sortName = "title"),
}
