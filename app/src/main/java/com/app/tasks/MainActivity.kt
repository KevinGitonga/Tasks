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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.app.tasks.core.base.EventsEffect
import com.app.tasks.core.data.preferences.SettingsDataStore
import com.app.tasks.navigation.MainNavGraph
import com.app.tasks.ui.theme.TasksAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Entry point for the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(settingsDataStore.appTheme.osValue)
        setContent {
            val state by mainViewModel.stateFlow.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            EventsEffect(viewModel = mainViewModel) { event ->
                when (event) {
                    is MainEvent.UpdateAppTheme -> {
                        AppCompatDelegate.setDefaultNightMode(event.osTheme)
                    }
                }
            }

            TasksAppTheme(theme = state.theme) {
                MainNavGraph(
                    navController = navController,
                )
            }
        }
    }
}
