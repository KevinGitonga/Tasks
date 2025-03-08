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

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.tasks.core.base.BaseViewModel
import com.app.tasks.core.constants.TaskStatuses
import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepository
import com.app.tasks.core.extensions.orNow
import com.app.tasks.core.extensions.orNullIfBlank
import com.app.tasks.navigation.NavigationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel
    @Inject
    constructor(
        private val localDataRepository: LocalDataRepository,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<AddTaskState, AddTaskEvent, AddTaskAction>(
            initialState =
                AddTaskState(
                    viewState = AddTaskState.ViewState.Success,
                    taskTitle = "",
                    taskDescription = "",
                    taskDueDate = ZonedDateTime.now(),
                    taskId = savedStateHandle.get<Int?>(NavigationConstants.Key.TASK_ID)?.toInt(),
                    navType = savedStateHandle.get<String>(NavigationConstants.Key.NAV_TYPE).toString(),
                ),
        ) {
        val taskPriorities = TaskPriorities.entries.toList()

        init {
            if (state.navType != NavigationConstants.Key.CREATE_TASK_NAV) {
                loadTaskDetails()
            }
        }

        private fun loadTaskDetails() {
            viewModelScope.launch {
                state.taskId?.let { it1 ->
                    localDataRepository.getSingleTask(it1).collectLatest { task ->
                        if (task != null && task.title.isNotBlank()) {
                            mutableStateFlow.update {
                                it.copy(
                                    taskTitle = task.title.orNullIfBlank() ?: state.taskTitle,
                                    taskDescription = task.description.orNullIfBlank() ?: state.taskDescription,
                                    taskDueDate = task.dueDate.orNow(),
                                    taskPriority =
                                        taskPriorities.first {
                                            it.priorityValue == (task.priority ?: state.taskPriority)
                                        },
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun handleAction(action: AddTaskAction) {
            when (action) {
                is AddTaskAction.DescriptionTextChange -> {
                    mutableStateFlow.update {
                        it.copy(taskDescription = action.description)
                    }
                }

                is AddTaskAction.TitleTextChange -> {
                    mutableStateFlow.update {
                        it.copy(taskTitle = action.title)
                    }
                }

                AddTaskAction.PrioritySelectionDialog -> {
                    mutableStateFlow.update {
                        it.copy(showPriorityDialog = true)
                    }
                }

                AddTaskAction.DatePickerDialogAction -> {
                    mutableStateFlow.update {
                        it.copy(showDatePickerDialog = true)
                    }
                }

                is AddTaskAction.TaskPrioritySelectionChange -> {
                    mutableStateFlow.update {
                        it.copy(
                            taskPriority = action.taskPriority,
                            showPriorityDialog = false,
                        )
                    }
                }

                is AddTaskAction.DueDateSelectionChange -> {
                    mutableStateFlow.update {
                        it.copy(
                            taskDueDate = action.dueDate,
                            showDatePickerDialog = false,
                        )
                    }
                }

                AddTaskAction.SaveTaskClick -> {
                    mutableStateFlow.update {
                        it.copy(showLoadingDialog = true)
                    }

                    saveTaskToDb()
                }

                AddTaskAction.TaskSavedASuccessAction -> {
                    mutableStateFlow.update {
                        it.copy(showSuccessDialog = false)
                    }
                    sendEvent(AddTaskEvent.NavigateBackEvent)
                }

                AddTaskAction.MarkTaskDoneAction -> {
                    mutableStateFlow.update {
                        it.copy(showLoadingDialog = true)
                    }

                    updateTaskStatus()
                }

                AddTaskAction.DismissDatePickerAction -> {
                    mutableStateFlow.update {
                        it.copy(showDatePickerDialog = false)
                    }
                }

                AddTaskAction.TaskPriorityDialogDismiss -> {
                    mutableStateFlow.update {
                        it.copy(showPriorityDialog = false)
                    }
                }

                AddTaskAction.DeleteTaskAction -> {
                    mutableStateFlow.update {
                        it.copy(showLoadingDialog = true)
                    }

                    deleteCompletedTask()
                }
            }
        }

        private fun deleteCompletedTask() {
            viewModelScope.launch {
                delay(1000)
                val deleteResponse =
                    state.taskId?.let {
                        localDataRepository.removeTask(
                            taskId = it,
                        )
                    }

                if (deleteResponse.toString().isNotBlank()) {
                    mutableStateFlow.update {
                        it.copy(
                            viewState = AddTaskState.ViewState.Success,
                            showLoadingDialog = false,
                            showSuccessDialog = true,
                        )
                    }
                }
            }
        }

        private fun updateTaskStatus() {
            viewModelScope.launch {
                delay(1000)

                val saveResponse =
                    state.taskId?.let {
                        localDataRepository.updateTaskStatus(
                            newStatus = TaskStatuses.Completed.statusName,
                            taskId = it,
                        )
                    }

                if (saveResponse.toString().isNotBlank()) {
                    mutableStateFlow.update {
                        it.copy(
                            viewState = AddTaskState.ViewState.Success,
                            showLoadingDialog = false,
                            showSuccessDialog = true,
                        )
                    }
                }
            }
        }

        private fun saveTaskToDb() {
            viewModelScope.launch {
                delay(1000)
                val saveResponse =
                    localDataRepository.saveTask(
                        TaskEntity(
                            title = state.taskTitle,
                            description = state.taskDescription,
                            priority = state.taskPriority.priorityValue,
                            dueDate = state.taskDueDate,
                            taskStatus = TaskStatuses.Pending.statusName,
                        ),
                    )

                if (saveResponse.toString().isNotBlank()) {
                    mutableStateFlow.update {
                        it.copy(
                            viewState = AddTaskState.ViewState.Success,
                            showLoadingDialog = false,
                            showSuccessDialog = true,
                        )
                    }
                }
            }
        }
    }

/**
 * Models state for the [AddTaskViewModel].
 */
data class AddTaskState(
    val viewState: ViewState,
    val taskPriority: TaskPriorities = TaskPriorities.Low,
    val taskTitle: String,
    val taskDescription: String,
    val taskDueDate: ZonedDateTime,
    val showPriorityDialog: Boolean = false,
    val showDatePickerDialog: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val showLoadingDialog: Boolean = false,
    val taskId: Int? = null,
    val navType: String = "create_task",
) {
    /**
     * Represents the specific view states for the [AddTaskScreen].
     */
    @Parcelize
    sealed class ViewState : Parcelable {
        /**
         * Represents an error state for the [AddTaskScreen].
         *
         * @property message Error message to display.
         */
        data class Error(
            val message: String,
        ) : ViewState()

        /**
         * Loading state for the [AddTaskScreen],
         * signifying that the content is being processed.
         */
        data object Loading : ViewState()

        /**
         * Represents a state where the [AddTaskScreen] has no items to display.
         */
        @Parcelize
        data object Success : ViewState()
    }
}

/**
 * Models events for the [AddTaskScreen].
 */
sealed class AddTaskEvent {
    /**
     * Event to trigger Toast show.
     */
    data object NavigateBackEvent : AddTaskEvent()
}

/**
 * Models actions for the [AddTaskScreen].
 */
sealed class AddTaskAction {
    /**
     * Fired when the name text input is changed.
     *
     * @property title The new name text.
     */
    data class TitleTextChange(val title: String) : AddTaskAction()

    /**
     * Fired when the description text input is changed.
     *
     * @property description The new name text.
     */
    data class DescriptionTextChange(val description: String) : AddTaskAction()

    /**
     * Fired when the task priority is changed.
     *
     */
    data object PrioritySelectionDialog : AddTaskAction()

    /**
     * Fired when the user initiates date selection.
     *
     */
    data object DatePickerDialogAction : AddTaskAction()

    /**
     * Fired when the user dismisses date picker dialog.
     *
     */
    data object DismissDatePickerAction : AddTaskAction()

    /**
     * Fired when the task priority selection is changed.
     *
     */
    data class TaskPrioritySelectionChange(val taskPriority: TaskPriorities) : AddTaskAction()

    /**
     * Fired when the user dismisses priority picker dialog.
     *
     */
    data object TaskPriorityDialogDismiss : AddTaskAction()

    /**
     * Fired when a due date for the task is selected.
     *
     */
    data class DueDateSelectionChange(val dueDate: ZonedDateTime) : AddTaskAction()

    /**
     * Fired when user clicks to save Task.
     *
     */
    data object SaveTaskClick : AddTaskAction()

    /**
     * Fired when user clicks to save Task.
     *
     */
    data object TaskSavedASuccessAction : AddTaskAction()

    /**
     * Fired when user marks task as done.
     *
     */
    data object MarkTaskDoneAction : AddTaskAction()

    /**
     * Fired when user clicks on delete task button.
     *
     */
    data object DeleteTaskAction : AddTaskAction()
}

enum class TaskPriorities(
    val priorityValue: Int,
) {
    Low(priorityValue = 1),
    Medium(priorityValue = 2),
    High(priorityValue = 3),
}
