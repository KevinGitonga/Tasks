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
package com.app.tasks.core.data.room.di

import android.content.Context
import androidx.room.Room
import com.app.tasks.core.data.room.convertors.ZonedDateTimeTypeConverter
import com.app.tasks.core.data.room.dao.TasksDao
import com.app.tasks.core.data.room.database.TasksDatabase
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepository
import com.app.tasks.core.data.room.localdatarepository.LocalDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides database dependencies in the vault package.
 */
@Module
@InstallIn(SingletonComponent::class)
class RoomDataModule {
    @Provides
    @Singleton
    fun provideTasksListDatabase(
        @ApplicationContext context: Context,
    ): TasksDatabase =
        Room
            .databaseBuilder(
                context = context,
                klass = TasksDatabase::class.java,
                name = "tasks_database",
            )
            .fallbackToDestructiveMigration()
            .addTypeConverter(ZonedDateTimeTypeConverter())
            .build()

    @Provides
    @Singleton
    fun provideTasksDao(database: TasksDatabase): TasksDao = database.tasksDao()

    @Provides
    @Singleton
    fun provideLocalDataRepository(
        tasksDao: TasksDao,
    ): LocalDataRepository =
        LocalDataRepositoryImpl(
            tasksDao = tasksDao,
        )
}
