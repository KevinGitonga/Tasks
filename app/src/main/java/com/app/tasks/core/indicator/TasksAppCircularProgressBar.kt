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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.tasks.ui.theme.TasksAppTheme

/**
 * A composable function that creates a circular progress bar with data usage information and a display text.
 *
 * @param name The name or label associated with the progress bar.
 * @param size The size of the circular progress bar.
 * @param foregroundIndicatorColor The color of the progress bar indicating the data usage.
 * @param shadowColor The color of the shadow surrounding the circular progress bar.
 * @param indicatorThickness The thickness of the progress bar indicator.
 * @param dataUsage The percentage of data usage to display.
 * @param animationDuration The duration of the animation for data usage change.
 * @param dataTextStyle The style for displaying the data usage text.
 */
@Composable
fun TasksAppCircularProgressbar(
    name: String = "",
    size: Dp = 110.dp,
    foregroundIndicatorColor: Color = TasksAppTheme.colorScheme.text.matchPendingText,
    shadowColor: Color = TasksAppTheme.colorScheme.background.scrim,
    indicatorThickness: Dp = 8.dp,
    dataUsage: Float = 60f,
    animationDuration: Int = 1000,
    dataTextStyle: TextStyle = TasksAppTheme.typography.titleSmall,
) {
    // State to hold the data usage value for animation
    var dataUsageRemember by remember {
        mutableFloatStateOf(-1f)
    }

    // State for animating the data usage value
    val dataUsageAnimate =
        animateFloatAsState(
            targetValue = dataUsageRemember,
            animationSpec =
                tween(
                    durationMillis = animationDuration,
                ),
            label = "",
        )

    // Trigger the LaunchedEffect to start the animation when the composable is first launched.
    LaunchedEffect(Unit) {
        dataUsageRemember = dataUsage
    }

    // Box to hold the entire composable
    Box(
        modifier =
            Modifier
                .size(size)
                .padding(top = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Canvas drawing for the circular progress bar
        Canvas(
            modifier = Modifier.size(size),
        ) {
            // Draw the shadow around the circular progress bar
            drawCircle(
                brush =
                    Brush.radialGradient(
                        colors = listOf(shadowColor, Color.LightGray),
                        center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                        radius = this.size.height / 2,
                    ),
                radius = this.size.height / 2,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2),
            )

            // Draw the white background of the circular progress bar
            drawCircle(
                color = Color.LightGray,
                radius = (size / 2 - indicatorThickness).toPx(),
                center = Offset(x = this.size.width / 2, y = this.size.height / 2),
            )

            // Calculate and draw the progress indicator
            val sweepAngle = (dataUsageAnimate.value) * 360 / 100
            drawArc(
                color = foregroundIndicatorColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                size =
                    Size(
                        width = (size - indicatorThickness).toPx(),
                        height = (size - indicatorThickness).toPx(),
                    ),
                topLeft =
                    Offset(
                        x = (indicatorThickness / 2).toPx(),
                        y = (indicatorThickness / 2).toPx(),
                    ),
            )
        }

        // Display text below the circular progress bar
        DisplayText(
            name = name,
            animateNumber = dataUsageAnimate,
            dataTextStyle = dataTextStyle,
        )
    }

    // Spacer to add some padding below the circular progress bar
    Spacer(modifier = Modifier.height(16.dp))
}

/**
 * A private composable function to display the name and data usage percentage text.
 *
 * @param name The name or label associated with the circular progress bar.
 * @param animateNumber The animated data usage percentage value.
 * @param dataTextStyle The style for displaying the data usage text.
 */
@Composable
private fun DisplayText(
    name: String,
    animateNumber: State<Float>,
    dataTextStyle: TextStyle,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
    ) {
        // Display the name with bold font and ellipsis for overflow
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            style = dataTextStyle,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        // Display the data usage percentage text
        Text(
            text = (animateNumber.value).toInt().toString() + "%",
            style = dataTextStyle,
            color = TasksAppTheme.colorScheme.text.primary,
        )
    }
}
