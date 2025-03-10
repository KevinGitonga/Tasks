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
package com.app.tasks.core

import androidx.appcompat.app.AppCompatDelegate

/**
 * Represents the theme options the user can set.
 *
 * The [value] is used for consistent storage purposes.
 */
enum class AppTheme(val value: String?, val osValue: Int) {
    DEFAULT(value = null, osValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    DARK(value = "dark", osValue = AppCompatDelegate.MODE_NIGHT_YES),
    LIGHT(value = "light", osValue = AppCompatDelegate.MODE_NIGHT_NO),
}
