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

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.tasks.modules.settings.SettingsScreen
import com.app.tasks.modules.taskdetails.AddTaskScreen
import com.app.tasks.modules.tasklist.TaskListScreen
import com.app.tasks.navigation.NavigationConstants.Key.NAV_TYPE
import com.app.tasks.navigation.NavigationConstants.Key.TASK_ID

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Task.route,
    ) {
        composable(route = Destinations.Task.route) {
            TaskListScreen(navController = navController)
        }

        composable(
            "${Destinations.TaskDetails.route}/{$TASK_ID}/{$NAV_TYPE}",
            enterTransition = {
                scaleIn(
                    spring(Spring.DampingRatioLowBouncy),
                    transformOrigin = TransformOrigin(1F, 0F),
                )
            },
            popExitTransition = {
                scaleOut(
                    spring(Spring.DampingRatioLowBouncy),
                    transformOrigin = TransformOrigin(1F, 0F),
                )
            },
            arguments =
                listOf(
                    navArgument(TASK_ID) {
                        type = NavType.IntType
                    },
                    navArgument(NAV_TYPE) {
                        type = NavType.StringType
                        defaultValue = "create_task"
                    },
                ),
        ) {
            AddTaskScreen(navController = navController)
        }

        composable(route = Destinations.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
