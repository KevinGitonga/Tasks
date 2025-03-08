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
package com.app.tasks.modules.settings

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.app.tasks.core.AppTheme
import com.app.tasks.core.base.BaseViewModel
import com.app.tasks.core.data.preferences.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

private const val KEY_STATE = "state"

/**
 * View model for the settings screen.
 */
@HiltViewModel
class SettingsViewModel
    @Inject
    constructor(
        private val settingsDataStore: SettingsDataStore,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SettingsState, SettingsEvent, SettingsAction>(
            initialState =
                savedStateHandle[KEY_STATE]
                    ?: SettingsState(
                        theme = settingsDataStore.appTheme,
                    ),
        ) {
        override fun handleAction(action: SettingsAction): Unit =
            when (action) {
                SettingsAction.BackClick -> handleBackClicked()
                is SettingsAction.ThemeChange -> handleThemeChanged(action)
            }

        private fun handleBackClicked() {
            sendEvent(SettingsEvent.NavigateBack)
        }

        private fun handleThemeChanged(action: SettingsAction.ThemeChange) {
            mutableStateFlow.update { it.copy(theme = action.theme) }
            settingsDataStore.appTheme = action.theme
        }
    }

/**
 * Models state of the Appearance screen.
 */
@Parcelize
data class SettingsState(
    val theme: AppTheme,
) : Parcelable

/**
 * Models events for the appearance screen.
 */
sealed class SettingsEvent {
    /**
     * Navigate back.
     */
    data object NavigateBack : SettingsEvent()
}

/**
 * Models actions for the appearance screen.
 */
sealed class SettingsAction {
    /**
     * User clicked back button.
     */
    data object BackClick : SettingsAction()

    /**
     * Indicates that the user selected a new theme.
     */
    data class ThemeChange(
        val theme: AppTheme,
    ) : SettingsAction()
}
