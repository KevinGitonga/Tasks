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
package com.app.tasks.core.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.tasks.core.data.room.convertors.ZonedDateTimeTypeConverter
import com.app.tasks.core.data.room.dao.TasksDao
import com.app.tasks.core.data.room.entities.TaskEntity

/**
 * Room database for storing any persisted data from the vault sync.
 */
@Database(
    entities = [
        TaskEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(ZonedDateTimeTypeConverter::class)
abstract class TasksDatabase : RoomDatabase() {
    /**
     * Provides the DAO for accessing tasks data.
     */
    abstract fun tasksDao(): TasksDao
}
