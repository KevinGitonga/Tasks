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
package com.app.tasks.core.indicator

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A countdown timer displayed to the user.
 *
 * @param timeLeftSeconds The seconds left on the timer.
 * @param periodSeconds The period for the timer countdown.
 * @param modifier A [Modifier] for the composable.
 */
@Composable
fun TasksAppCircularCountdownIndicator(
    timeLeftSeconds: Int,
    periodSeconds: Int,
    modifier: Modifier = Modifier,
) {
    val progressAnimate by animateFloatAsState(
        targetValue = timeLeftSeconds.toFloat() / periodSeconds,
        animationSpec =
            tween(
                durationMillis = periodSeconds,
                delayMillis = 0,
                easing = LinearOutSlowInEasing,
            ),
        label = "CircularCountDownAnimation",
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            progress = { progressAnimate },
            modifier = Modifier.size(size = 30.dp),
            color = TasksAppTheme.colorScheme.icon.secondary,
            trackColor = Color.Transparent,
            strokeWidth = 3.dp,
            strokeCap = StrokeCap.Round,
        )

        Text(
            text = timeLeftSeconds.toString(),
            style = TasksAppTheme.typography.bodySmall,
            color = TasksAppTheme.colorScheme.text.primary,
        )
    }
}
