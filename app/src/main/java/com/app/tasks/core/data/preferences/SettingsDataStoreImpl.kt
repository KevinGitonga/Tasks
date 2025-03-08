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
import com.app.tasks.core.AppTheme
import com.app.tasks.core.utils.bufferedMutableSharedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onSubscription

private const val APP_THEME_KEY = "theme"
private const val ACCOUNT_BIOMETRIC_INTEGRITY_VALID_KEY = "accountBiometricIntegrityValid"

/**
 * Primary implementation of [SettingsDataStore].
 */
@Suppress("TooManyFunctions")
class SettingsDataStoreImpl(
    val sharedPreferences: SharedPreferences,
) : BaseDataStore(sharedPreferences = sharedPreferences),
    SettingsDataStore {
    private val mutableAppThemeFlow = bufferedMutableSharedFlow<AppTheme>(replay = 1)

    override var appTheme: AppTheme
        get() =
            getString(key = APP_THEME_KEY)
                ?.let { storedValue ->
                    AppTheme.entries.firstOrNull { storedValue == it.value }
                }
                ?: AppTheme.DEFAULT
        set(newValue) {
            putString(
                key = APP_THEME_KEY,
                value = newValue.value,
            )
            mutableAppThemeFlow.tryEmit(appTheme)
        }

    override val appThemeFlow: Flow<AppTheme>
        get() =
            mutableAppThemeFlow
                .onSubscription { emit(appTheme) }

    override fun clearData(userId: String) {
        removeWithPrefix(prefix = ACCOUNT_BIOMETRIC_INTEGRITY_VALID_KEY.appendIdentifier(userId))
    }
}
