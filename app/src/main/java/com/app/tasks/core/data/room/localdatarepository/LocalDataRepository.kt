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

import com.app.tasks.core.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Primary access point for disk information related to vault data.
 */
@Suppress("TooManyFunctions")
interface LocalDataRepository {
    /**
     * Saves a Task to the data source.
     */
    suspend fun saveTask(taskEntity: TaskEntity): Long

    /**
     * Retrieves all Tasks from the data source.
     */
    fun getTasks(): Flow<List<TaskEntity>>

    /**
     * Deletes Tasks Data.
     */
    suspend fun deleteTasks(): Int

    /**
     * Remove Task associated with Task id.
     */
    suspend fun removeTask(taskId: Int): Int

    /**
     * Get Task with Task id.
     */
    fun getSingleTask(taskId: Int): Flow<TaskEntity>

    /**
     * Get Task with Task id.
     */
    suspend fun updateTaskStatus(newStatus: String, taskId: Int): Int
}
