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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.tasks.core.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides methods for inserting, retrieving, and deleting tasks from the database using the
 * [TaskEntity].
 */
@Dao
interface TasksDao {
    /**
     * Inserts task into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity): Long

    /**
     * Retrieves all tasks from the database.
     */
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    /**
     * Deletes all the locally stored tasks, This will return the
     * number of rows deleted by this query.
     */
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks(): Int

    /**
     * Deletes Task associated with the given [taskId]. This will return the
     * number of rows deleted by this query.
     */
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun removeTask(taskId: Int): Int

    /**
     * Get Task associated with the given [taskId]. This will return the
     */
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getSingleTask(taskId: Int): Flow<TaskEntity>

    /**
     * Update Task associated with the given [taskId]. This will return the
     * number of rows affected by this query.
     */
    @Query("UPDATE tasks SET status=:newStatus WHERE id=:taskId")
    suspend fun updateTaskStatus(taskId: Int, newStatus: String): Int
}
