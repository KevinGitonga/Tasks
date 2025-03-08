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
package com.app.tasks.core.data.room.dao

import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.utils.bufferedMutableSharedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeTasksDao : TasksDao {
    val affectedRowsDummy = 1
    val storedTasks = mutableListOf<TaskEntity>()

    var isDeleteCalled = false
    var isInsertCalled = false
    var isUpdateCalled = false

    private val tasksFlow = bufferedMutableSharedFlow<List<TaskEntity>>(replay = 1)

    init {
        tasksFlow.tryEmit(emptyList())
    }

    override suspend fun insertTask(taskEntity: TaskEntity): Long {
        storedTasks.add(taskEntity)
        tasksFlow.tryEmit(storedTasks.toList())
        isInsertCalled = true
        return affectedRowsDummy.toLong()
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return tasksFlow
    }

    override suspend fun deleteAllTasks(): Int {
        val count = storedTasks.count()
        storedTasks.clear()
        isDeleteCalled = true
        return count
    }

    override suspend fun removeTask(taskId: Int): Int {
        isDeleteCalled = true
        storedTasks.removeAll { it.taskId == taskId }
        tasksFlow.tryEmit(storedTasks.toList())
        return affectedRowsDummy
    }

    override fun getSingleTask(taskId: Int): Flow<TaskEntity> {
        return tasksFlow.map { tasks -> tasks.first { it.taskId == taskId } }
    }

    override suspend fun updateTaskStatus(taskId: Int, newStatus: String): Int {
        isUpdateCalled = false
        val task = storedTasks.first { it.taskId == taskId }
        val taskCopy =
            TaskEntity(
                taskId = task.taskId,
                title = task.title,
                description = task.description,
                priority = task.priority,
                dueDate = task.dueDate,
                taskStatus = "completed",
            )
        storedTasks.remove(task)
        storedTasks.add(taskCopy)
        tasksFlow.tryEmit(storedTasks.toList())
        return affectedRowsDummy
    }
}
