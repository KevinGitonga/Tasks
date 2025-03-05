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
package com.app.tasks.core.extensions

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.util.lerp

/**
 * Returns the correct color for a scrolled container based on the given [TopAppBarScrollBehavior]
 * and target [expandedColor] / [collapsedColor].
 */
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarScrollBehavior.toScrolledContainerColor(
    expandedColor: Color,
    collapsedColor: Color,
): Color {
    val progressFraction =
        if (this.isPinned) {
            this.state.overlappedFraction
        } else {
            this.state.collapsedFraction
        }
    return lerp(
        start = expandedColor,
        stop = collapsedColor,
        // The easing function here matches what is currently in TopAppBarColors.containerColor and
        // is necessary to match to the app bar color through the full range of motion.
        fraction = FastOutLinearInEasing.transform(progressFraction),
    )
}

/**
 * Returns the correct alpha, as a [Float], for a containers alpha based on the given
 * [TopAppBarScrollBehavior].
 */
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarScrollBehavior.toScrolledContainerDividerAlpha(): Float {
    val progressFraction =
        if (this.isPinned) {
            this.state.overlappedFraction
        } else {
            this.state.collapsedFraction
        }
    return lerp(
        start = 0f,
        stop = 1f,
        // The easing function here matches what is currently in TopAppBarColors.containerColor
        fraction = FastOutLinearInEasing.transform(fraction = progressFraction),
    )
}
