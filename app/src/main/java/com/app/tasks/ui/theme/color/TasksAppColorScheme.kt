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
package com.app.tasks.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Defines all the colors for the app.
 */
@Immutable
data class TasksAppColorScheme(
    val text: TextColors,
    val background: BackgroundColors,
    val stroke: StrokeColors,
    val icon: IconColors,
    val filledButton: FilledButtonColors,
    val outlineButton: OutlineButtonColors,
    val toggleButton: ToggleButtonColors,
    val sliderButton: SliderButtonColors,
    val status: StatusColors,
) {
    /**
     * Defines all the text colors for the app.
     */
    @Immutable
    data class TextColors(
        val primary: Color,
        val secondary: Color,
        val interaction: Color,
        val reversed: Color,
        val codePink: Color,
        val codeBlue: Color,
        val matchPendingText: Color,
        val matchLostText: Color,
        val matchWonText: Color,
        val matchCancelledText: Color,
    )

    /**
     * Defines all the background colors for the app.
     */
    @Immutable
    data class BackgroundColors(
        val primary: Color,
        val secondary: Color,
        val tertiary: Color,
        val alert: Color,
        val scrim: Color,
        val pressed: Color,
    )

    /**
     * Defines all the stroke colors for the app.
     */
    @Immutable
    data class StrokeColors(
        val divider: Color,
        val border: Color,
        val segmentedNav: Color,
    )

    /**
     * Defines all the icons colors for the app.
     */
    @Immutable
    data class IconColors(
        val primary: Color,
        val secondary: Color,
        val reversed: Color,
        val badgeBackground: Color,
        val badgeForeground: Color,
    )

    /**
     * Defines all the filled button colors for the app.
     */
    @Immutable
    data class FilledButtonColors(
        val background: Color,
        val backgroundDisabled: Color,
        val backgroundReversed: Color,
        val foreground: Color,
        val foregroundDisabled: Color,
        val foregroundReversed: Color,
    )

    /**
     * Defines all the outline button colors for the app.
     */
    @Immutable
    data class OutlineButtonColors(
        val border: Color,
        val borderDisabled: Color,
        val borderReversed: Color,
        val foreground: Color,
        val foregroundDisabled: Color,
        val foregroundReversed: Color,
    )

    /**
     * Defines all the toggle colors for the app.
     */
    @Immutable
    data class ToggleButtonColors(
        val backgroundOn: Color,
        val backgroundOff: Color,
        val switch: Color,
    )

    /**
     * Defines all the slider colors for the app.
     */
    @Immutable
    data class SliderButtonColors(
        val knobBackground: Color,
        val knobLabel: Color,
        val filled: Color,
        val unfilled: Color,
    )

    /**
     * Defines all the status colors for the app.
     */
    @Immutable
    data class StatusColors(
        val strong: Color,
        val good: Color,
        val weak1: Color,
        val weak2: Color,
        val error: Color,
    )
}
