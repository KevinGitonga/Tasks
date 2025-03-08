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
package com.app.tasks.core.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Base class for simplifying interactions with [SharedPreferences].
 */
@Suppress("UnnecessaryAbstractClass")
abstract class BaseDataStore(
    private val sharedPreferences: SharedPreferences,
) {
    protected fun getString(key: String): String? = sharedPreferences.getString(key.withBase(), null)

    protected fun putString(
        key: String,
        value: String?,
    ): Unit = sharedPreferences.edit { putString(key.withBase(), value) }

    protected fun removeWithPrefix(prefix: String) {
        sharedPreferences
            .all
            .keys
            .filter { it.startsWith(prefix.withBase()) }
            .forEach { sharedPreferences.edit { remove(it) } }
    }

    protected fun String.appendIdentifier(identifier: String): String = "${this}_$identifier"

    /**
     * Helper method for prepending the key with the appropriate base storage key.
     */
    private fun String.withBase(): String = "bwPreferencesStorage:$this"
}
