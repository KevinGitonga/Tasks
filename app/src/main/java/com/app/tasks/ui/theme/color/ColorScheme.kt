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

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.app.tasks.ui.theme.color.PrimitiveColors.matchPendingColor

/**
 * The default [TasksAppColorScheme] for dark mode.
 */
val darkTasksAppColorScheme: TasksAppColorScheme =
    TasksAppColorScheme(
        text =
            TasksAppColorScheme.TextColors(
                primary = PrimitiveColors.gray200,
                secondary = PrimitiveColors.gray600,
                interaction = PrimitiveColors.blue400,
                reversed = PrimitiveColors.gray1200,
                codePink = PrimitiveColors.pink200,
                codeBlue = PrimitiveColors.blue400,
                matchPendingText = matchPendingColor,
                matchLostText = PrimitiveColors.matchLostColor,
                matchWonText = PrimitiveColors.matchWonColor,
                matchCancelledText = PrimitiveColors.matchCancelledColor,
            ),
        background =
            TasksAppColorScheme.BackgroundColors(
                primary = PrimitiveColors.gray1200,
                secondary = PrimitiveColors.gray1100,
                tertiary = PrimitiveColors.gray1000,
                alert = PrimitiveColors.gray300,
                scrim = PrimitiveColors.gray1400.copy(alpha = 0.4f),
                pressed = PrimitiveColors.gray500,
            ),
        stroke =
            TasksAppColorScheme.StrokeColors(
                divider = PrimitiveColors.gray900,
                border = PrimitiveColors.blue400,
                segmentedNav = PrimitiveColors.gray900,
            ),
        icon =
            TasksAppColorScheme.IconColors(
                primary = PrimitiveColors.gray500,
                secondary = PrimitiveColors.blue400,
                reversed = PrimitiveColors.gray1100,
                badgeBackground = PrimitiveColors.pink200,
                badgeForeground = PrimitiveColors.gray1100,
            ),
        filledButton =
            TasksAppColorScheme.FilledButtonColors(
                background = PrimitiveColors.blue400,
                backgroundDisabled = PrimitiveColors.gray900,
                backgroundReversed = PrimitiveColors.gray1100,
                foreground = PrimitiveColors.gray1100,
                foregroundDisabled = PrimitiveColors.gray500,
                foregroundReversed = PrimitiveColors.blue400,
            ),
        outlineButton =
            TasksAppColorScheme.OutlineButtonColors(
                border = PrimitiveColors.blue400,
                borderDisabled = PrimitiveColors.gray900,
                borderReversed = PrimitiveColors.gray1100,
                foreground = PrimitiveColors.blue400,
                foregroundDisabled = PrimitiveColors.gray900,
                foregroundReversed = PrimitiveColors.gray1100,
            ),
        toggleButton =
            TasksAppColorScheme.ToggleButtonColors(
                backgroundOn = PrimitiveColors.blue400,
                backgroundOff = PrimitiveColors.gray900,
                switch = PrimitiveColors.gray100,
            ),
        sliderButton =
            TasksAppColorScheme.SliderButtonColors(
                knobBackground = PrimitiveColors.blue400,
                knobLabel = PrimitiveColors.gray1100,
                filled = PrimitiveColors.blue400,
                unfilled = PrimitiveColors.gray900,
            ),
        status =
            TasksAppColorScheme.StatusColors(
                strong = PrimitiveColors.green200,
                good = PrimitiveColors.blue400,
                weak1 = PrimitiveColors.red200,
                weak2 = PrimitiveColors.yellow200,
                error = PrimitiveColors.red200,
            ),
    )

/**
 * The default [TasksAppColorScheme] for light mode.
 */
val lightTasksAppColorScheme: TasksAppColorScheme =
    TasksAppColorScheme(
        text =
            TasksAppColorScheme.TextColors(
                primary = PrimitiveColors.gray1300,
                secondary = PrimitiveColors.gray700,
                interaction = PrimitiveColors.blue500,
                reversed = PrimitiveColors.gray100,
                codePink = PrimitiveColors.pink100,
                codeBlue = PrimitiveColors.blue500,
                matchPendingText = matchPendingColor,
                matchLostText = PrimitiveColors.matchLostColor,
                matchWonText = PrimitiveColors.matchWonColor,
                matchCancelledText = PrimitiveColors.matchCancelledColor,
            ),
        background =
            TasksAppColorScheme.BackgroundColors(
                primary = PrimitiveColors.gray200,
                secondary = PrimitiveColors.gray100,
                tertiary = PrimitiveColors.blue100,
                alert = PrimitiveColors.blue700,
                scrim = PrimitiveColors.gray1400.copy(alpha = 0.4f),
                pressed = PrimitiveColors.gray1000,
            ),
        stroke =
            TasksAppColorScheme.StrokeColors(
                divider = PrimitiveColors.gray400,
                border = PrimitiveColors.blue500,
                segmentedNav = PrimitiveColors.blue100,
            ),
        icon =
            TasksAppColorScheme.IconColors(
                primary = PrimitiveColors.gray700,
                secondary = PrimitiveColors.blue500,
                reversed = PrimitiveColors.gray100,
                badgeBackground = PrimitiveColors.pink100,
                badgeForeground = PrimitiveColors.gray100,
            ),
        filledButton =
            TasksAppColorScheme.FilledButtonColors(
                background = PrimitiveColors.blue500,
                backgroundDisabled = PrimitiveColors.gray400,
                backgroundReversed = PrimitiveColors.gray100,
                foreground = PrimitiveColors.gray100,
                foregroundDisabled = PrimitiveColors.gray500,
                foregroundReversed = PrimitiveColors.blue500,
            ),
        outlineButton =
            TasksAppColorScheme.OutlineButtonColors(
                border = PrimitiveColors.blue500,
                borderDisabled = PrimitiveColors.gray500,
                borderReversed = PrimitiveColors.gray100,
                foreground = PrimitiveColors.blue500,
                foregroundDisabled = PrimitiveColors.gray500,
                foregroundReversed = PrimitiveColors.gray100,
            ),
        toggleButton =
            TasksAppColorScheme.ToggleButtonColors(
                backgroundOn = PrimitiveColors.blue500,
                backgroundOff = PrimitiveColors.gray500,
                switch = PrimitiveColors.gray100,
            ),
        sliderButton =
            TasksAppColorScheme.SliderButtonColors(
                knobBackground = PrimitiveColors.blue500,
                knobLabel = PrimitiveColors.gray100,
                filled = PrimitiveColors.blue500,
                unfilled = PrimitiveColors.gray300,
            ),
        status =
            TasksAppColorScheme.StatusColors(
                strong = PrimitiveColors.green300,
                good = PrimitiveColors.blue500,
                weak1 = PrimitiveColors.red300,
                weak2 = PrimitiveColors.yellow300,
                error = PrimitiveColors.red300,
            ),
    )

/**
 * Creates a [TasksAppColorScheme] for dark mode based on dynamic Material You colors.
 */
@Suppress("LongMethod")
fun dynamicTasksAppColorScheme(
    materialColorScheme: ColorScheme,
    isDarkTheme: Boolean,
): TasksAppColorScheme {
    val defaultTheme =
        if (isDarkTheme) darkTasksAppColorScheme else lightTasksAppColorScheme
    return TasksAppColorScheme(
        text =
            TasksAppColorScheme.TextColors(
                primary = materialColorScheme.onBackground,
                secondary = materialColorScheme.onSurface,
                interaction = materialColorScheme.primary,
                reversed = materialColorScheme.onTertiary,
                codePink = defaultTheme.text.codePink,
                codeBlue = defaultTheme.text.codeBlue,
                matchPendingText = defaultTheme.text.matchPendingText,
                matchLostText = defaultTheme.text.matchLostText,
                matchWonText = defaultTheme.text.matchWonText,
                matchCancelledText = defaultTheme.text.matchCancelledText,
            ),
        background =
            TasksAppColorScheme.BackgroundColors(
                primary = materialColorScheme.background,
                secondary = materialColorScheme.surfaceContainer,
                tertiary = materialColorScheme.surfaceContainerHighest,
                alert = materialColorScheme.error,
                scrim = materialColorScheme.scrim,
                pressed = materialColorScheme.onSurfaceVariant,
            ),
        stroke =
            TasksAppColorScheme.StrokeColors(
                divider = materialColorScheme.outlineVariant,
                border = materialColorScheme.primary,
                segmentedNav = materialColorScheme.outline,
            ),
        icon =
            TasksAppColorScheme.IconColors(
                primary = materialColorScheme.onBackground,
                secondary = materialColorScheme.primary,
                reversed = materialColorScheme.inversePrimary,
                badgeBackground = materialColorScheme.error,
                badgeForeground = materialColorScheme.onError,
            ),
        filledButton =
            TasksAppColorScheme.FilledButtonColors(
                background = materialColorScheme.primary,
                backgroundDisabled = materialColorScheme.onSurface.copy(alpha = 0.12f),
                backgroundReversed = materialColorScheme.surfaceContainerHighest,
                foreground = materialColorScheme.onPrimary,
                foregroundDisabled = materialColorScheme.onSurface.copy(alpha = 0.38f),
                foregroundReversed = materialColorScheme.onSurface,
            ),
        outlineButton =
            TasksAppColorScheme.OutlineButtonColors(
                border = materialColorScheme.outlineVariant,
                borderDisabled = materialColorScheme.outlineVariant,
                borderReversed = materialColorScheme.outlineVariant,
                foreground = materialColorScheme.primary,
                foregroundDisabled = materialColorScheme.onSurface,
                foregroundReversed = materialColorScheme.inversePrimary,
            ),
        toggleButton =
            TasksAppColorScheme.ToggleButtonColors(
                backgroundOn = materialColorScheme.primary,
                backgroundOff = materialColorScheme.surfaceContainerHighest,
                switch = materialColorScheme.onPrimaryContainer,
            ),
        sliderButton =
            TasksAppColorScheme.SliderButtonColors(
                knobBackground = materialColorScheme.primary,
                knobLabel = materialColorScheme.onPrimary,
                filled = materialColorScheme.primary,
                unfilled = materialColorScheme.secondaryContainer,
            ),
        status =
            TasksAppColorScheme.StatusColors(
                strong = defaultTheme.status.strong,
                good = defaultTheme.status.good,
                weak1 = defaultTheme.status.weak1,
                weak2 = defaultTheme.status.weak2,
                error = defaultTheme.status.error,
            ),
    )
}

/**
 * Derives a Material [ColorScheme] from the [TasksAppColorScheme] using the [defaultColorScheme]
 * as a baseline.
 */
fun TasksAppColorScheme.toMaterialColorScheme(defaultColorScheme: ColorScheme): ColorScheme =
    defaultColorScheme.copy(
        primary = this.stroke.border,
        onSurfaceVariant = this.text.secondary,
    )

/**
 * The raw colors that support the [TasksAppColorScheme].
 */
private data object PrimitiveColors {
    val gray100: Color = Color(color = 0xFFFFFFFF)
    val gray200: Color = Color(color = 0xFFF3F6F9)
    val gray300: Color = Color(color = 0xFFE6E9EF)
    val gray400: Color = Color(color = 0xFFD3D9E3)
    val gray500: Color = Color(color = 0xFF96A3BB)
    val gray600: Color = Color(color = 0xFF8898B5)
    val gray700: Color = Color(color = 0xFF5A6D91)
    val gray800: Color = Color(color = 0xFF79808E)
    val gray900: Color = Color(color = 0xFF525B6A)
    val gray1000: Color = Color(color = 0xFF303946)
    val gray1100: Color = Color(color = 0xFF202733)
    val gray1200: Color = Color(color = 0xFF121A27)
    val gray1300: Color = Color(color = 0xFF1B2029)
    val gray1400: Color = Color(color = 0xFF000000)

    val blue100: Color = Color(color = 0xFFDBE5F6)
    val blue200: Color = Color(color = 0xFFAAC3EF)
    val blue300: Color = Color(color = 0xFF79A1E9)
    val blue400: Color = Color(color = 0xFF65ABFF)
    val blue500: Color = Color(color = 0xFF175DDC)
    val blue600: Color = Color(color = 0xFF1A41AC)
    val blue700: Color = Color(color = 0xFF020F66)

    val green100: Color = Color(color = 0xFFBFECC3)
    val green200: Color = Color(color = 0xFF6BF178)
    val green300: Color = Color(color = 0xFF0C8018)
    val green400: Color = Color(color = 0xFF08540F)

    val red100: Color = Color(color = 0xFFFFECEF)
    val red200: Color = Color(color = 0xFFFF4E63)
    val red300: Color = Color(color = 0xFFCB263A)
    val red400: Color = Color(color = 0xFF951B2A)

    val yellow100: Color = Color(color = 0xFFFFF8E4)
    val yellow200: Color = Color(color = 0xFFFFBF00)
    val yellow300: Color = Color(color = 0xFFAC5800)

    val pink100: Color = Color(color = 0xFFC01176)
    val pink200: Color = Color(color = 0xFFFF8FD0)

    val matchWonColor: Color = Color(color = 0xFF4BC26A)
    val matchLostColor: Color = Color(color = 0xFFFF4C52)
    val matchCancelledColor: Color = Color(color = 0xFF737373)
    val matchPendingColor: Color = Color(color = 0xFF1D66AF)
}
