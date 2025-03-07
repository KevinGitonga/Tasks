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
package com.app.tasks.core.data.room.localdatarepository

import com.app.tasks.core.data.room.dao.TasksDao
import com.app.tasks.core.data.room.entities.TaskEntity
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepository
import kotlinx.coroutines.flow.Flow

/**
 * Default implementation of [LocalDataRepository].
 */
@Suppress("TooManyFunctions", "LongParameterList")
class LocalDataRepositoryImpl(
    private val tasksDao: TasksDao,
) : LocalDataRepository {
    override suspend fun saveTask(taskEntity: TaskEntity): Long {
        return tasksDao.insertTask(
            taskEntity,
        )
    }

    override fun getTasks(): Flow<List<TaskEntity>> {
        return tasksDao.getAllTasks()
    }

    override suspend fun deleteTasks(): Int {
        return tasksDao.deleteAllTasks()
    }

    override suspend fun removeTask(taskId: Int): Int {
        return tasksDao.removeTask(taskId = taskId)
    }

    override fun getSingleTask(taskId: Int): Flow<TaskEntity> {
        return tasksDao.getSingleTask(taskId = taskId)
    }

    override suspend fun updateTaskStatus(newStatus: String, taskId: Int): Int {
        return tasksDao.updateTaskStatus(taskId = taskId, newStatus = newStatus)
    }
}
