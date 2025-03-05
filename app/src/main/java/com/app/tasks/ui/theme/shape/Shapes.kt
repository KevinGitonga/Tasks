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
package com.app.tasks.ui.theme.shape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * The default [TasksAppShapes] for the app.
 */
val soccerScoresShapes: TasksAppShapes =
    TasksAppShapes(
        actionCard = RoundedCornerShape(size = 12.dp),
        bottomSheet = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        coachMark = RoundedCornerShape(size = 8.dp),
        content = RoundedCornerShape(size = 8.dp),
        contentBottom = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        contentTop = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        dialog = RoundedCornerShape(size = 28.dp),
        fab = CircleShape,
        infoCallout = RoundedCornerShape(size = 8.dp),
        menu = RoundedCornerShape(size = 4.dp),
        segmentedControl = CircleShape,
        snackbar = RoundedCornerShape(size = 8.dp),
        contentMiddle = RoundedCornerShape(0.dp),
    )
