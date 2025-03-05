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
package com.app.tasks.ui.theme

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.app.tasks.core.AppTheme
import com.app.tasks.ui.theme.color.TasksAppColorScheme
import com.app.tasks.ui.theme.color.darkTasksAppColorScheme
import com.app.tasks.ui.theme.color.dynamicTasksAppColorScheme
import com.app.tasks.ui.theme.color.lightTasksAppColorScheme
import com.app.tasks.ui.theme.color.toMaterialColorScheme
import com.app.tasks.ui.theme.shape.TasksAppShapes
import com.app.tasks.ui.theme.shape.soccerScoresShapes
import com.app.tasks.ui.theme.type.TasksAppTypography
import com.app.tasks.ui.theme.type.soccerScoresTypography
import com.app.tasks.ui.theme.type.toMaterialTypography

/**
 * Static wrapper to make accessing the theme components easier.
 */
object TasksAppTheme {
    /**
     * Retrieves the current [TasksAppColorScheme].
     */
    val colorScheme: TasksAppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalTasksAppColorScheme.current

    /**
     * Retrieves the current [TasksAppShapes].
     */
    val shapes: TasksAppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalTasksAppShapes.current

    /**
     * Retrieves the current [TasksAppTypography].
     */
    val typography: TasksAppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTasksAppTypography.current
}

/**
 * The overall application theme. This can be configured to support a [theme] and [dynamicColor].
 */
@Composable
fun TasksAppTheme(
    theme: AppTheme = AppTheme.DEFAULT,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val darkTheme =
        when (theme) {
            AppTheme.DEFAULT -> isSystemInDarkTheme()
            AppTheme.DARK -> true
            AppTheme.LIGHT -> false
        }

    // Get the current scheme
    val context = LocalContext.current
    val materialColorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) {
                    dynamicDarkColorScheme(context = context)
                } else {
                    dynamicLightColorScheme(context = context)
                }
            }

            darkTheme -> darkColorScheme()
            else -> lightColorScheme()
        }
    val soccerScoresColorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                dynamicTasksAppColorScheme(
                    materialColorScheme = materialColorScheme,
                    isDarkTheme = darkTheme,
                )
            }

            darkTheme -> darkTasksAppColorScheme
            else -> lightTasksAppColorScheme
        }

    // Update status bar according to scheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !darkTheme
            insetsController.isAppearanceLightNavigationBars = !darkTheme
            window.setBackgroundDrawable(
                ColorDrawable(
                    soccerScoresColorScheme.background.primary.value
                        .toInt(),
                ),
            )
        }
    }

    CompositionLocalProvider(
        LocalTasksAppColorScheme provides soccerScoresColorScheme,
        LocalTasksAppShapes provides soccerScoresShapes,
        LocalTasksAppTypography provides soccerScoresTypography,
    ) {
        MaterialTheme(
            colorScheme =
                soccerScoresColorScheme.toMaterialColorScheme(
                    defaultColorScheme = materialColorScheme,
                ),
            typography = soccerScoresTypography.toMaterialTypography(),
            content = content,
        )
    }
}

/**
 * Provides access to the soccerScores colors throughout the app.
 */
val LocalTasksAppColorScheme: ProvidableCompositionLocal<TasksAppColorScheme> =
    compositionLocalOf { lightTasksAppColorScheme }

/**
 * Provides access to the TasksApp shapes throughout the app.
 */
val LocalTasksAppShapes: ProvidableCompositionLocal<TasksAppShapes> =
    compositionLocalOf { soccerScoresShapes }

/**
 * Provides access to the TasksApp typography throughout the app.
 */
val LocalTasksAppTypography: ProvidableCompositionLocal<TasksAppTypography> =
    compositionLocalOf { soccerScoresTypography }
