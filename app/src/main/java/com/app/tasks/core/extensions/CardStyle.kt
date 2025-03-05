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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines the possible display options for a card background on a composable component.
 */
sealed class CardStyle {
    /**
     * Indicates if a divider should be displayed in this card.
     */
    abstract val hasDivider: Boolean

    /**
     * Provides start padding to the divider when present.
     */
    abstract val dividerPadding: Dp

    /**
     * Defines a top card that has top rounded corners.
     */
    data class Top(
        override val hasDivider: Boolean = true,
        override val dividerPadding: Dp = 16.dp,
    ) : CardStyle()

    /**
     * Defines a middle card that has no rounded corners.
     */
    data class Middle(
        override val hasDivider: Boolean = true,
        override val dividerPadding: Dp = 16.dp,
    ) : CardStyle()

    /**
     * Defines a bottom card that has bottom rounded corners.
     */
    data object Bottom : CardStyle() {
        override val hasDivider: Boolean = false
        override val dividerPadding: Dp = 0.dp
    }

    /**
     * Defines a full card that has rounded corners.
     */
    data object Full : CardStyle() {
        override val hasDivider: Boolean = false
        override val dividerPadding: Dp = 0.dp
    }
}
