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

import android.content.res.Configuration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Provides the maximum height [Dp] common for all dialogs with a given [Configuration].
 */
val Configuration.maxDialogHeight: Dp
    get() =
        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 312.dp
            Configuration.ORIENTATION_PORTRAIT -> 542.dp
            Configuration.ORIENTATION_UNDEFINED -> Dp.Unspecified
            @Suppress("DEPRECATION")
            Configuration
                
                
                
                .
                ORIENTATION_SQUARE,
            -> Dp.Unspecified

            else -> Dp.Unspecified
        }
