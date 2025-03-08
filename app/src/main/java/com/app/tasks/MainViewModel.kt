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
package com.app.tasks

import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.app.tasks.core.AppTheme
import com.app.tasks.core.base.BaseViewModel
import com.app.tasks.core.data.preferences.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

/**
 * A view model that helps launch actions for the [MainActivity].
 */
@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        settingsDataStore: SettingsDataStore,
    ) : BaseViewModel<MainState, MainEvent, MainAction>(
            initialState =
                MainState(
                    theme = settingsDataStore.appTheme,
                ),
        ) {
        init {
            settingsDataStore
                .appThemeFlow
                .onEach { trySendAction(MainAction.Internal.ThemeUpdate(it)) }
                .launchIn(viewModelScope)
        }

        override fun handleAction(action: MainAction) {
            when (action) {
                is MainAction.Internal.ThemeUpdate -> handleAppThemeUpdated(action)
            }
        }

        private fun handleAppThemeUpdated(action: MainAction.Internal.ThemeUpdate) {
            mutableStateFlow.update { it.copy(theme = action.theme) }
            sendEvent(MainEvent.UpdateAppTheme(osTheme = action.theme.osValue))
        }
    }

/**
 * Models state for the [MainActivity].
 */
@Parcelize
data class MainState(
    val theme: AppTheme,
) : Parcelable

/**
 * Models actions for the [MainActivity].
 */
sealed class MainAction {
    /**
     * Actions for internal use by the ViewModel.
     */
    sealed class Internal : MainAction() {
        /**
         * Indicates that the app theme has changed.
         */
        data class ThemeUpdate(
            val theme: AppTheme,
        ) : Internal()
    }
}

/**
 * Represents events that are emitted by the [MainViewModel].
 */
sealed class MainEvent {
    /**
     * Indicates that the app theme has been updated.
     */
    data class UpdateAppTheme(
        val osTheme: Int,
    ) : MainEvent()
}
