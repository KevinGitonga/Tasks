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

import com.app.tasks.core.data.room.dao.FakeTasksDao
import com.app.tasks.core.data.room.entities.TaskEntity
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val TASK_1: TaskEntity = mockk()

class LocalDataRepositoryTest {
    private lateinit var tasksDao: FakeTasksDao
    private lateinit var localDataRepository: LocalDataRepository

    @BeforeEach
    fun setup() {
        tasksDao = FakeTasksDao()
        localDataRepository =
            LocalDataRepositoryImpl(
                tasksDao = tasksDao,
            )
    }

    @Test
    fun `saveTask should call insertTask`() =
        runTest {
            assertFalse(tasksDao.isInsertCalled)
            assertEquals(0, tasksDao.storedTasks.size)

            localDataRepository.saveTask(TASK_1)

            assertTrue(tasksDao.isInsertCalled)
            assertEquals(1, tasksDao.storedTasks.size)
        }
}
