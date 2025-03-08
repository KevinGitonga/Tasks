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
package com.app.tasks.navigation

import com.app.tasks.navigation.NavigationConstants.Screen.SETTINGS
import com.app.tasks.navigation.NavigationConstants.Screen.SUBTASK
import com.app.tasks.navigation.NavigationConstants.Screen.TASK

sealed class Destinations(val route: String) {
    data object Task : Destinations(TASK)

    data object TaskDetails : Destinations(SUBTASK)

    data object Settings : Destinations(SETTINGS)
}

object NavigationConstants {
    object Screen {
        const val TASK = "task"
        const val SUBTASK = "task-details"
        const val SETTINGS = "settings"
    }

    object Key {
        const val TASK_ID = "task_id"
        const val NAV_TYPE = "nav_type"

        const val CREATE_TASK_NAV = "create_task"
        const val EDIT_TASK_NAV = "edit_task"
        const val DELETE_TASK_NAV = "delete_task"

        const val CREATE_TASK_DUMMY_ID = -9
    }
}
