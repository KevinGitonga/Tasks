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
import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
                ),
        ) {
        init {
            loadTasks()
        }

        private fun loadTasks() {
            mutableStateFlow.update {
                val dataList =
                    localDataRepository.getTasks()
                        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

                Timber.e("Tasks Loaded ${dataList.value}")

                if (dataList.value.isNotEmpty()) {
                    it.copy(viewState = TaskListState.ViewState.Content(dataList.value))
                } else {
                    it.copy(viewState = TaskListState.ViewState.NoItems)
                }
            }
        }

        override fun handleAction(action: TaskListAction) {
            when (action) {
                TaskListAction.FixturesTabClick -> {
                }
            }
        }
    }

/**
 * Models state for the [TaskListViewModel].
 */
data class TaskListState(
    val viewState: ViewState,
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
            val taskListData: List<TaskEntity>,
        ) : ViewState()
    }
}

/**
 * Models events for the bottom tab of the vault unlocked portion of the app.
 */
sealed class TaskListEvents {
    /**
     * Navigate to the Fixtures screen.
     */
    data object NavigateToTaskDetailsScreen : TaskListEvents()
}

/**
 * Models actions for the bottom tab of the vault unlocked portion of the app.
 */
sealed class TaskListAction {
    /**
     * Click Generator tab.
     */
    data object FixturesTabClick : TaskListAction()
}
